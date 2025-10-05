package br.com.sosysters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sosysters.dto.GeneroDto;
import br.com.sosysters.entities.Genero;
import br.com.sosysters.repositories.GeneroRepository;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository generoRepository;

	public List<GeneroDto> listGeneros() {
		List<Genero> result = generoRepository.findAll();
		List<GeneroDto> dto = result.stream().map(x -> new GeneroDto(x)).toList();
		return dto;
	}
}