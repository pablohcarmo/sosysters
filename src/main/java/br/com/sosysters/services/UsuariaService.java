package br.com.sosysters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	@Autowired
	private final UsuariaRepository usuariaRepository;

	@Autowired
	private EtniaRepository etniaRepository;

	@Autowired
	private GeneroRepository generoRepository;


	public List<UsuariaDto> findAll() {
		List<Usuaria> result = usuariaRepository.findAll();
		List<UsuariaDto> dto = result.stream().map(x -> new UsuariaDto(x)).toList();
		return dto;
	}

	public UsuariaService(UsuariaRepository usuariaRepository) {
		this.usuariaRepository = usuariaRepository;
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
		usuaria.setDtNascimentoUsuaria(dto.getDtNascimentoUsuaria());
		usuaria.setRgUsuaria(dto.getRgUsuaria());
		usuaria.setCpfUsuaria(dto.getCpfUsuaria());
		usuaria.setEmailUsuaria(dto.getEmailUsuaria());
		usuaria.setSenhaUsuaria(dto.getSenhaUsuaria());
		usuaria.setEtniaUsuaria(etnia);
		usuaria.setGeneroUsuaria(genero);
		usuariaRepository.save(usuaria);
	}
}