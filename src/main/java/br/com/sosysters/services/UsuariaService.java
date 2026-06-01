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
import br.com.sosysters.dto.MeuCadastroDto;
import br.com.sosysters.dto.UsuariaDto;
import br.com.sosysters.dto.TrocarSenhaDto;
import br.com.sosysters.exceptions.InvalidCurrentPasswordException;
import org.springframework.transaction.annotation.Transactional;
import br.com.sosysters.entities.Etnia;
import br.com.sosysters.entities.Estado;
import br.com.sosysters.entities.Cidade;
import br.com.sosysters.entities.Bairro;
import br.com.sosysters.entities.Logradouro;
import br.com.sosysters.entities.Endereco;
import br.com.sosysters.entities.Celular;
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
		return result.stream().map(UsuariaDto::new).toList();
	}

	@Transactional(readOnly = true)
	public MeuCadastroDto carregarMeuCadastro(String email) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		MeuCadastroDto dto = new MeuCadastroDto();
		dto.setNomeUsuaria(usuaria.getNomeUsuaria());
		dto.setSobrenomeUsuaria(usuaria.getSobrenomeUsuaria());
		dto.setNomeSocialUsuaria(usuaria.getNomeSocialUsuaria());
		dto.setDtNascimentoUsuaria(usuaria.getDtNascimentoUsuaria() != null ? usuaria.getDtNascimentoUsuaria().toLocalDate() : null);
		dto.setRgUsuaria(usuaria.getRgUsuaria());
		dto.setCpfUsuaria(usuaria.getCpfUsuaria());
		dto.setEmailUsuaria(usuaria.getEmailUsuaria());
		dto.setEtniaUsuaria(usuaria.getEtniaUsuaria() != null ? usuaria.getEtniaUsuaria().getIdEtnia() : null);
		dto.setGeneroUsuaria(usuaria.getGeneroUsuaria() != null ? usuaria.getGeneroUsuaria().getIdGenero() : null);

		celularRepository.findFirstByUsuariaCelular_IdUsuaria(usuaria.getIdUsuaria())
				.ifPresent(celular -> dto.setTelefoneCompleto(formatarTelefone(celular.getDdd(), celular.getCelular())));

		usuaria.getEnderecos().stream().findFirst().ifPresent(endereco -> {
			dto.setCep(endereco.getCep());
			dto.setNumeroResidencia(endereco.getNumResidencia());
			dto.setComplemento(endereco.getComplemento());
			if (endereco.getLogradouroEndereco() != null) {
				dto.setLogradouro(endereco.getLogradouroEndereco().getLogradouro());
			}
			if (endereco.getBairroEndereco() != null) {
				dto.setBairro(endereco.getBairroEndereco().getBairro());
				if (endereco.getBairroEndereco().getCidadeBairro() != null) {
					dto.setCidade(endereco.getBairroEndereco().getCidadeBairro().getCidade());
					if (endereco.getBairroEndereco().getCidadeBairro().getEstadoCidade() != null) {
						dto.setEstado(endereco.getBairroEndereco().getCidadeBairro().getEstadoCidade().getUfEstado());
					}
				}
			}
		});

		return dto;
	}

	public UsuariaService(UsuariaRepository usuariaRepository, PasswordEncoder passwordEncoder) {
		this.usuariaRepository = usuariaRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		return usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(username)
				.orElseThrow( () -> new UsernameNotFoundException("Usuário não encontrado!"));
	}

	@Transactional
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
		usuaria.setCpfUsuaria(dto.getCpfUsuaria() != null ? dto.getCpfUsuaria().replaceAll("\\D", "") : null);
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

	@Transactional
	public void salvarFotosVerificacao(String email, byte[] selfie, byte[] documento) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		if ((selfie == null || selfie.length == 0) || (documento == null || documento.length == 0)) {
			throw new IllegalArgumentException("Ambas as imagens (selfie e documento) são obrigatórias.");
		}

		Foto foto = fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria).orElseGet(Foto::new);
		foto.setUsuaria(usuaria);
		foto.setSelfieVerificacao(selfie);
		foto.setDocumentoVerificacao(documento);
		foto.setIdentidadeVerificada(false);
		foto.setDataVerificacao(LocalDateTime.now());
		fotoRepository.save(foto);
	}

	@Transactional(readOnly = true)
	public boolean verificarIdentidadeVerificada(String email) {
		try {
			Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
					.orElse(null);
			if (usuaria == null) {
				return false;
			}
			Optional<Foto> foto = fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria);
			return foto.map(Foto::isIdentidadeVerificada).orElse(false);
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public void aprovarIdentidade(String email) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
		Foto foto = fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria)
				.orElseThrow(() -> new IllegalStateException("Nenhum envio de verificação encontrado."));
		foto.setIdentidadeVerificada(true);
		fotoRepository.save(foto);
	}

	@Transactional
	public void reprovarIdentidade(String email) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
		fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria).ifPresent(foto -> {
			foto.setIdentidadeVerificada(false);
			fotoRepository.save(foto);
		});
	}
}