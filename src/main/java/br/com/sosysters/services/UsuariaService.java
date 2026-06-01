package br.com.sosysters.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;
import java.util.UUID;
import java.util.Optional;

import br.com.sosysters.entities.Foto;
import br.com.sosysters.entities.UsuariaConfirmacaoToken;
import br.com.sosysters.repositories.UsuariaConfirmacaoTokenRepository;
import br.com.sosysters.repositories.FotoRepository;
import br.com.sosysters.repositories.CelularRepository;
import br.com.sosysters.repositories.EnderecoRepository;
import br.com.sosysters.repositories.BairroRepository;
import br.com.sosysters.repositories.CidadeRepository;
import br.com.sosysters.repositories.EstadoRepository;
import br.com.sosysters.repositories.LogradouroRepository;
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
import jakarta.servlet.http.HttpServletRequest;

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

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private LogradouroRepository logradouroRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CelularRepository celularRepository;

	@Autowired
	private FotoRepository fotoRepository;

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
	public void cadastrarUsuaria(NovaUsuariaDto dto, HttpServletRequest request) {
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
		usuaria = usuariaRepository.save(usuaria);

		persistirTelefone(dto, usuaria);
		persistirEndereco(dto, usuaria);

		UsuariaConfirmacaoToken verificador = new UsuariaConfirmacaoToken();
		verificador.setUsuaria(usuaria);
		verificador.setUuid(UUID.randomUUID());
		verificador.setDataExpiracao(Instant.now().plusSeconds(3600)); // Token válido por 1 hora
		usuariaConfirmacaoTokenRepository.save(verificador);

		// Envia email após salvar usuária e token
		String baseUrl = request.getScheme() + "://" + request.getServerName();
		if ((request.getScheme().equals("http") && request.getServerPort() != 80) ||
		    (request.getScheme().equals("https") && request.getServerPort() != 443)) {
			baseUrl += ":" + request.getServerPort();
		}
		String link = baseUrl + "/verify-email/" + verificador.getUuid().toString();
		String corpo = "Olá " + usuaria.getNomeUsuaria() + ",\n\n" +
				"Clique no link abaixo para confirmar seu cadastro:\n" + link + "\n\n" +
				"Este link é válido por 1 hora.\n" +
				"Se o link não funcionar, copie e cole no seu navegador.\n\n" +
				"Atenciosamente,\n" + "A equipe do SOSysters";
		try {
			emailService.enviarEmail(usuaria.getEmailUsuaria(), "Confirme seu cadastro no SOSysters", corpo);
		} catch (Exception e) {
			System.err.println("Erro ao enviar email de confirmação: " + e.getMessage());
		}


		/*emailService.enviarEmail(usuaria.getEmailUsuaria(), "Bem-vinda ao SOSysters!", "Olá " + usuaria.getNomeUsuaria() + ",\n\n" +
				"Seja bem-vinda ao SOSysters! Estamos felizes em tê-la conosco. Agradecemos por se juntar à nossa comunidade e esperamos que você encontre apoio, recursos e conexões valiosas aqui.\n\n" +
				"Se precisar de ajuda ou tiver alguma dúvida, não hesite em entrar em contato conosco. Estamos aqui para ajudar!\n\n" +
				"Atenciosamente,\n" +
				"A equipe do SOSysters");*/
	}

	private void persistirTelefone(NovaUsuariaDto dto, Usuaria usuaria) {
		if (dto.getDddCelular() == null || dto.getDddCelular().isBlank() || dto.getCelular() == null || dto.getCelular().isBlank()) {
			return;
		}

		Celular celular = new Celular();
		celular.setDdd(Integer.valueOf(dto.getDddCelular().replaceAll("\\D", "")));
		celular.setCelular(Integer.valueOf(dto.getCelular().replaceAll("\\D", "")));
		celular.setUsuariaCelular(usuaria);
		celularRepository.save(celular);
	}

	private void persistirEndereco(NovaUsuariaDto dto, Usuaria usuaria) {
		if (dto.getCep() == null || dto.getCep().isBlank()
				|| dto.getLogradouro() == null || dto.getLogradouro().isBlank()
				|| dto.getBairro() == null || dto.getBairro().isBlank()
				|| dto.getCidade() == null || dto.getCidade().isBlank()
				|| dto.getEstado() == null || dto.getEstado().isBlank()) {
			return;
		}

		Estado estado = estadoRepository.findFirstByUfEstadoIgnoreCaseOrderByIdEstadoAsc(dto.getEstado())
				.orElseGet(() -> {
					Estado novo = new Estado();
					novo.setUfEstado(dto.getEstado());
					novo.setEstado(dto.getEstado());
					return estadoRepository.save(novo);
				});

		Cidade cidade = cidadeRepository.findFirstByCidadeIgnoreCaseAndEstadoCidadeOrderByIdCidadeAsc(dto.getCidade(), estado)
				.orElseGet(() -> {
					Cidade nova = new Cidade();
					nova.setCidade(dto.getCidade());
					nova.setEstadoCidade(estado);
					return cidadeRepository.save(nova);
				});

		Bairro bairro = bairroRepository.findFirstByBairroIgnoreCaseAndCidadeBairroOrderByIdBairroAsc(dto.getBairro(), cidade)
				.orElseGet(() -> {
					Bairro novo = new Bairro();
					novo.setBairro(dto.getBairro());
					novo.setCidadeBairro(cidade);
					return bairroRepository.save(novo);
				});

		Logradouro logradouro = logradouroRepository.findFirstByLogradouroIgnoreCaseOrderByIdLogradouroAsc(dto.getLogradouro())
				.orElseGet(() -> {
					Logradouro novo = new Logradouro();
					novo.setLogradouro(dto.getLogradouro());
					return logradouroRepository.save(novo);
				});

		Endereco endereco = new Endereco();
		endereco.setNumResidencia(dto.getNumeroResidencia());
		endereco.setComplemento(dto.getComplemento());
		endereco.setCep(dto.getCep());
		endereco.setBairroEndereco(bairro);
		endereco.setLogradouroEndereco(logradouro);
		endereco = enderecoRepository.save(endereco);

		usuaria.getEnderecos().add(endereco);
		usuariaRepository.save(usuaria);
	}

	@Transactional
	public void atualizarMeuCadastro(String email, MeuCadastroDto dto) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		usuaria.setNomeUsuaria(dto.getNomeUsuaria());
		usuaria.setSobrenomeUsuaria(dto.getSobrenomeUsuaria());
		usuaria.setNomeSocialUsuaria(dto.getNomeSocialUsuaria());
		if (dto.getDtNascimentoUsuaria() != null) {
			usuaria.setDtNascimentoUsuaria(Date.valueOf(dto.getDtNascimentoUsuaria()));
		}
		usuaria.setRgUsuaria(dto.getRgUsuaria());
		usuaria.setCpfUsuaria(dto.getCpfUsuaria() != null ? dto.getCpfUsuaria().replaceAll("\\D", "") : null);
		usuaria.setEmailUsuaria(dto.getEmailUsuaria());

		if (dto.getEtniaUsuaria() != null) {
			Etnia etnia = etniaRepository.findById(dto.getEtniaUsuaria())
					.orElseThrow(() -> new RuntimeException("Etnia não encontrada"));
			usuaria.setEtniaUsuaria(etnia);
		}
		if (dto.getGeneroUsuaria() != null) {
			Genero genero = generoRepository.findById(dto.getGeneroUsuaria())
					.orElseThrow(() -> new RuntimeException("Gênero não encontrada"));
			usuaria.setGeneroUsuaria(genero);
		}

		usuariaRepository.save(usuaria);
		atualizarTelefone(usuaria, dto.getTelefoneCompleto());
		atualizarEndereco(usuaria, dto);
	}

	private void atualizarTelefone(Usuaria usuaria, String telefoneCompleto) {
		if (telefoneCompleto == null || telefoneCompleto.isBlank()) {
			return;
		}

		String digits = telefoneCompleto.replaceAll("\\D", "");
		if (digits.length() < 10) {
			throw new IllegalArgumentException("Informe um telefone válido com DDD.");
		}

		Integer ddd = Integer.valueOf(digits.substring(0, 2));
		Integer numero = Integer.valueOf(digits.substring(2));

		Celular celular = celularRepository.findFirstByUsuariaCelular_IdUsuaria(usuaria.getIdUsuaria())
				.orElseGet(Celular::new);
		celular.setDdd(ddd);
		celular.setCelular(numero);
		celular.setUsuariaCelular(usuaria);
		celularRepository.save(celular);
	}

	private void atualizarEndereco(Usuaria usuaria, MeuCadastroDto dto) {
		if (dto.getCep() == null || dto.getCep().isBlank()
				|| dto.getLogradouro() == null || dto.getLogradouro().isBlank()
				|| dto.getBairro() == null || dto.getBairro().isBlank()
				|| dto.getCidade() == null || dto.getCidade().isBlank()
				|| dto.getEstado() == null || dto.getEstado().isBlank()
				|| dto.getNumeroResidencia() == null || dto.getNumeroResidencia().isBlank()) {
			return;
		}

		Estado estado = estadoRepository.findFirstByUfEstadoIgnoreCaseOrderByIdEstadoAsc(dto.getEstado())
				.orElseGet(() -> {
					Estado novo = new Estado();
					novo.setUfEstado(dto.getEstado());
					novo.setEstado(dto.getEstado());
					return estadoRepository.save(novo);
				});

		Cidade cidade = cidadeRepository.findFirstByCidadeIgnoreCaseAndEstadoCidadeOrderByIdCidadeAsc(dto.getCidade(), estado)
				.orElseGet(() -> {
					Cidade nova = new Cidade();
					nova.setCidade(dto.getCidade());
					nova.setEstadoCidade(estado);
					return cidadeRepository.save(nova);
				});

		Bairro bairro = bairroRepository.findFirstByBairroIgnoreCaseAndCidadeBairroOrderByIdBairroAsc(dto.getBairro(), cidade)
				.orElseGet(() -> {
					Bairro novo = new Bairro();
					novo.setBairro(dto.getBairro());
					novo.setCidadeBairro(cidade);
					return bairroRepository.save(novo);
				});

		Logradouro logradouro = logradouroRepository.findFirstByLogradouroIgnoreCaseOrderByIdLogradouroAsc(dto.getLogradouro())
				.orElseGet(() -> {
					Logradouro novo = new Logradouro();
					novo.setLogradouro(dto.getLogradouro());
					return logradouroRepository.save(novo);
				});

		Endereco endereco = usuaria.getEnderecos().stream().findFirst().orElseGet(Endereco::new);
		endereco.setNumResidencia(dto.getNumeroResidencia());
		endereco.setComplemento(dto.getComplemento());
		endereco.setCep(dto.getCep());
		endereco.setBairroEndereco(bairro);
		endereco.setLogradouroEndereco(logradouro);
		endereco = enderecoRepository.save(endereco);

		if (!usuaria.getEnderecos().contains(endereco)) {
			usuaria.getEnderecos().clear();
			usuaria.getEnderecos().add(endereco);
			usuariaRepository.save(usuaria);
		}
	}

	private String formatarTelefone(Integer ddd, Integer celular) {
		String dddFormatado = String.format("%02d", ddd);
		String numero = String.valueOf(celular);
		if (numero.length() <= 4) {
			return "(" + dddFormatado + ") " + numero;
		}
		String prefixo = numero.substring(0, numero.length() - 4);
		String sufixo = numero.substring(numero.length() - 4);
		return "(" + dddFormatado + ") " + prefixo + "-" + sufixo;
	}

	@Transactional(readOnly = true)
	public Optional<Foto> carregarFotoPerfil(String email) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
		return fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria);
	}

	@Transactional
	public void salvarFotoPerfil(String email, byte[] imagemPerfil) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		if (imagemPerfil == null || imagemPerfil.length == 0) {
			throw new IllegalArgumentException("A imagem enviada está vazia.");
		}

		Foto foto = fotoRepository.findFirstByUsuariaOrderByIdFotoDesc(usuaria).orElseGet(Foto::new);
		foto.setUsuaria(usuaria);
		foto.setImgPerfil(imagemPerfil);
		fotoRepository.save(foto);
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
				return "Link expirado!";
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
	public String reenviarEmailConfirmacao(String emailUsuaria, HttpServletRequest request) {
		if (emailUsuaria == null || emailUsuaria.isBlank()) {
			return "Informe um email válido para reenviar a confirmação.";
		}

		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(emailUsuaria)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

		if (usuaria.isUsuariaVerificado()) {
			return "Esta conta já foi confirmada. Você pode fazer login normalmente.";
		}

		UsuariaConfirmacaoToken token = usuariaConfirmacaoTokenRepository.findByUsuaria(usuaria)
				.orElseGet(UsuariaConfirmacaoToken::new);
		token.setUsuaria(usuaria);
		token.setUuid(UUID.randomUUID());
		token.setDataExpiracao(Instant.now().plusSeconds(3600)); // Token válido por 1 hora
		usuariaConfirmacaoTokenRepository.save(token);

		String baseUrl = request.getScheme() + "://" + request.getServerName();
		if ((request.getScheme().equals("http") && request.getServerPort() != 80) ||
		    (request.getScheme().equals("https") && request.getServerPort() != 443)) {
			baseUrl += ":" + request.getServerPort();
		}
		String link = baseUrl + "/verify-email/" + token.getUuid();
		String corpo = "Olá " + usuaria.getNomeUsuaria() + ",\n\n"
				+ "Seu link anterior expirou ou você solicitou um novo link de confirmação.\n"
				+ "Clique no link abaixo para confirmar seu cadastro:\n" + link + "\n\n"
				+ "Este link é válido por 1 hora.\n\n"
				+ "Atenciosamente,\nA equipe do SOSysters";

		try {
			emailService.enviarEmail(usuaria.getEmailUsuaria(), "Reenvio de confirmação SOSysters", corpo);
			return "Email de confirmação reenviado. Verifique sua caixa de entrada.";
		} catch (Exception e) {
			return "Não foi possível reenviar o email de confirmação no momento.";
		}
	}

	@Transactional
	public void trocarSenha(String email, TrocarSenhaDto dto) {
		var opt = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email);
		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		Usuaria usuaria = opt.get();

		if (dto.getCurrentPassword() == null || dto.getCurrentPassword().isBlank() ||
				dto.getNewPassword() == null || dto.getNewPassword().isBlank()) {
			throw new IllegalArgumentException("Preencha a senha atual e a nova senha.");
		}

		if (!passwordEncoder.matches(dto.getCurrentPassword(), usuaria.getSenhaUsuaria())) {
			throw new InvalidCurrentPasswordException("Senha atual incorreta.");
		}

		if (passwordEncoder.matches(dto.getNewPassword(), usuaria.getSenhaUsuaria())) {
			throw new IllegalArgumentException("A nova senha deve ser diferente da atual.");
		}

		usuaria.setSenhaUsuaria(passwordEncoder.encode(dto.getNewPassword()));
		usuariaRepository.save(usuaria);
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

	@Transactional
	public String enviarLinkResetSenha(String email, HttpServletRequest request) {
		Optional<Usuaria> opt = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email);
		if (opt.isEmpty()) {
			return "Se este email estiver cadastrado, você receberá um link para resetar a senha.";
		}

		Usuaria usuaria = opt.get();
		UsuariaConfirmacaoToken token = usuariaConfirmacaoTokenRepository.findByUsuaria(usuaria)
				.orElseGet(UsuariaConfirmacaoToken::new);
		token.setUsuaria(usuaria);
		token.setUuid(UUID.randomUUID());
		token.setDataExpiracao(Instant.now().plusSeconds(3600)); // Token válido por 1 hora
		usuariaConfirmacaoTokenRepository.save(token);

		String baseUrl = request.getScheme() + "://" + request.getServerName();
		if ((request.getScheme().equals("http") && request.getServerPort() != 80) ||
		    (request.getScheme().equals("https") && request.getServerPort() != 443)) {
			baseUrl += ":" + request.getServerPort();
		}
		String link = baseUrl + "/reset-senha/" + token.getUuid();
		String corpo = "Olá " + usuaria.getNomeUsuaria() + ",\n\n"
				+ "Você solicitou a recuperação de senha.\n"
				+ "Clique no link abaixo para redefinir sua senha:\n" + link + "\n\n"
				+ "Este link é válido por 1 hora.\n"
				+ "Se você não solicitou isto, ignore este email.\n\n"
				+ "Atenciosamente,\nA equipe do SOSysters";

		try {
			emailService.enviarEmail(usuaria.getEmailUsuaria(), "Recuperação de Senha SOSysters", corpo);
			return "Se este email estiver cadastrado, você receberá um link para resetar a senha.";
		} catch (Exception e) {
			return "Se este email estiver cadastrado, você receberá um link para resetar a senha.";
		}
	}

	public String validarTokenResetSenha(String uuid) {
		try {
			var opt = usuariaConfirmacaoTokenRepository.findByUuid(UUID.fromString(uuid));
			if (opt.isEmpty()) {
				return "Token inválido";
			}

			UsuariaConfirmacaoToken token = opt.get();
			if (Instant.now().isAfter(token.getDataExpiracao())) {
				return "Link expirado! Solicite um novo link de recuperação.";
			}

			return "valid";
		} catch (IllegalArgumentException e) {
			return "Formato de token inválido";
		}
	}

	@Transactional
	public String resetarSenha(String uuid, String novaSenha) {
		try {
			var opt = usuariaConfirmacaoTokenRepository.findByUuid(UUID.fromString(uuid));
			if (opt.isEmpty()) {
				return "Token inválido";
			}

			UsuariaConfirmacaoToken token = opt.get();
			if (Instant.now().isAfter(token.getDataExpiracao())) {
				usuariaConfirmacaoTokenRepository.delete(token);
				return "Link expirado! Solicite um novo link de recuperação.";
			}

			Usuaria usuaria = token.getUsuaria();
			usuaria.setSenhaUsuaria(passwordEncoder.encode(novaSenha));
			usuariaRepository.save(usuaria);
			usuariaConfirmacaoTokenRepository.delete(token);

			return "Senha atualizada com sucesso!";
		} catch (IllegalArgumentException e) {
			return "Formato de token inválido";
		}
	}
}