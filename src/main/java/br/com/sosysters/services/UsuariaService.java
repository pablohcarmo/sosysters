package br.com.sosysters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sosysters.dto.UsuariaDto;
import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.repositories.UsuariaRepository;

@Service
public class UsuariaService {
	@Autowired
	private UsuariaRepository usuariaRepository;

	public List<UsuariaDto> findAll() {
		List<Usuaria> result = usuariaRepository.findAll();
		List<UsuariaDto> dto = result.stream().map(x -> new UsuariaDto(x)).toList();
		return dto;
	}
}