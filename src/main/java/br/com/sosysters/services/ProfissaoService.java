package br.com.sosysters.services;

import br.com.sosysters.entities.Prestadora;
import br.com.sosysters.entities.Profissao;
import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.repositories.PrestadoraRepository;
import br.com.sosysters.repositories.ProfissaoRepository;
import br.com.sosysters.repositories.UsuariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfissaoService {

	@Autowired
	private ProfissaoRepository profissaoRepository;

	@Autowired
	private PrestadoraRepository prestadoraRepository;

	@Autowired
	private UsuariaRepository usuariaRepository;

	public List<Profissao> listarTodas() {
		return profissaoRepository.findAll();
	}

	public Profissao buscarPorId(Long idProfissao) {
		return profissaoRepository.findById(idProfissao)
				.orElseThrow(() -> new IllegalArgumentException("Profissão não encontrada."));
	}

	@Transactional
	public Profissao carregarProfissao(String email) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuária não encontrada."));

		Prestadora prestadora = prestadoraRepository.findByUsuaria(usuaria)
				.orElse(null);

		if (prestadora == null || prestadora.getProfissoes().isEmpty()) {
			return null;
		}

		return prestadora.getProfissoes().stream().findFirst().orElse(null);
	}

	@Transactional
	public void salvarProfissao(String email, Long idProfissao) {
		Usuaria usuaria = usuariaRepository.findFirstByEmailUsuariaIgnoreCaseOrderByIdUsuariaAsc(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuária não encontrada."));

		Prestadora prestadora = prestadoraRepository.findByUsuaria(usuaria)
				.orElseGet(() -> {
					Prestadora novaPrestadora = new Prestadora(usuaria);
					return prestadoraRepository.save(novaPrestadora);
				});

		prestadora.getProfissoes().clear();

		if (idProfissao != null) {
			Profissao profissao = buscarPorId(idProfissao);
			prestadora.adicionarProfissao(profissao);
		}

		prestadoraRepository.save(prestadora);
	}
}

