package br.com.sosysters.services;

import java.time.Instant;
import java.util.List;
import java.sql.Date;
import java.util.UUID;

import br.com.sosysters.entities.UsuariaConfirmacaoToken;
import br.com.sosysters.repositories.UsuariaConfirmacaoTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sosysters.dto.NovaUsuariaDto;
import br.com.sosysters.dto.UsuariaDto;
import br.com.sosysters.entities.Etnia;
import br.com.sosysters.entities.Genero;
import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.repositories.EtniaRepository;
import br.com.sosysters.repositories.GeneroRepository;
import br.com.sosysters.repositories.UsuariaRepository;

@Service
public class UsuariaService implements UserDetailsService {
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final UsuariaRepository usuariaRepository;

	@Autowired
	private UsuariaConfirmacaoTokenRepository usuariaConfirmacaoTokenRepository;

	@Autowired
	private EtniaRepository etniaRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private EmailService emailService;

	public List<UsuariaDto> findAll() {
		List<Usuaria> result = usuariaRepository.findAll();
		List<UsuariaDto> dto = result.stream().map(x -> new UsuariaDto(x)).toList();
		return dto;
	}

	public UsuariaService(UsuariaRepository usuariaRepository, PasswordEncoder passwordEncoder) {
		this.usuariaRepository = usuariaRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		return usuariaRepository.findByEmailUsuariaIgnoreCase(username)
				.orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado!"));
	}

	public void cadastrarUsuaria(NovaUsuariaDto dto) {
		Etnia etnia = etniaRepository.findById(dto.getEtniaUsuaria())
									.orElseThrow(() -> new RuntimeException("Etnia não encontrada"));
		Genero genero = generoRepository.findById(dto.getGeneroUsuaria())
				.orElseThrow(() -> new RuntimeException("Gênero não encontrada"));

		Usuaria usuaria = new Usuaria();
		usuaria.setNomeUsuaria(dto.getNomeUsuaria());
		usuaria.setSobrenomeUsuaria(dto.getSobrenomeUsuaria());
		usuaria.setNomeSocialUsuaria(dto.getNomeSocialUsuaria());
		if (dto.getDtNascimentoUsuaria() != null) {
			usuaria.setDtNascimentoUsuaria(Date.valueOf(dto.getDtNascimentoUsuaria()));
		}
		usuaria.setRgUsuaria(dto.getRgUsuaria());
		usuaria.setCpfUsuaria(dto.getCpfUsuaria());
		usuaria.setEmailUsuaria(dto.getEmailUsuaria());
		usuaria.setSenhaUsuaria(passwordEncoder.encode(dto.getSenhaUsuaria()));
		usuaria.setEtniaUsuaria(etnia);
		usuaria.setGeneroUsuaria(genero);

		// Primeiro persiste a usuária para que o token aponte para uma entidade gerenciada
		usuariaRepository.save(usuaria);

		UsuariaConfirmacaoToken verificador = new UsuariaConfirmacaoToken();
		verificador.setUsuaria(usuaria);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusSeconds(900)); // Token válido por 15 minutos
		usuariaConfirmacaoTokenRepository.save(verificador);

		// Envia email após salvar usuária e token
		String link = "http://localhost:8080/verify-email/" + verificador.getUuid().toString();
		String corpo = "Olá " + usuaria.getNomeUsuaria() + ",\n\n" +
				"Clique no link abaixo para confirmar seu cadastro:\n" + link + "\n\n" +
				"Se o link não funcionar, copie e cole no seu navegador.\n\n" +
				"Atenciosamente,\n" + "A equipe do SOSysters";
		emailService.enviarEmail(usuaria.getEmailUsuaria(), "Confirme seu cadastro no SOSysters", corpo);


		/*emailService.enviarEmail(usuaria.getEmailUsuaria(), "Bem-vinda ao SOSysters!", "Olá " + usuaria.getNomeUsuaria() + ",\n\n" +
				"Seja bem-vinda ao SOSysters! Estamos felizes em tê-la conosco. Agradecemos por se juntar à nossa comunidade e esperamos que você encontre apoio, recursos e conexões valiosas aqui.\n\n" +
				"Se precisar de ajuda ou tiver alguma dúvida, não hesite em entrar em contato conosco. Estamos aqui para ajudar!\n\n" +
				"Atenciosamente,\n" +
				"A equipe do SOSysters");*/
	}

	public String verificaToken(String uuid) {
		try {
			// Busca o token pelo UUID
			var opt = usuariaConfirmacaoTokenRepository.findByUuid(UUID.fromString(uuid));
			if (opt.isEmpty()) {
				return "Token inválido";
			}

			UsuariaConfirmacaoToken usuariaVerificacao = opt.get();

			// Verifica expiração
			if (Instant.now().isAfter(usuariaVerificacao.getDataExpiracao())) {
				// Token expirado: remove do banco e informa
				usuariaConfirmacaoTokenRepository.delete(usuariaVerificacao);
				return "Link expirado. Por favor, solicite um novo link de verificação!";
			}

			// Marca a usuária como verificada e persiste
			Usuaria usuaria = usuariaVerificacao.getUsuaria();
			usuaria.setUsuariaVerificado(true);
			usuariaRepository.save(usuaria);

			// Remove o token após uso
			usuariaConfirmacaoTokenRepository.delete(usuariaVerificacao);

			return "Email verificado com sucesso! Sua conta está ativada.";
		} catch (IllegalArgumentException e) {
			return "Formato de UUID inválido";
		}
	}
}