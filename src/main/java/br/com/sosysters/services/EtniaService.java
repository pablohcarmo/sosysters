package br.com.sosysters.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sosysters.dto.EtniaDto;
import br.com.sosysters.entities.Etnia;
import br.com.sosysters.repositories.EtniaRepository;

@Service
public class EtniaService {
	@Autowired
	private EtniaRepository etniaRepository;

	public List<EtniaDto> listEtnias() {
		List<Etnia> result = etniaRepository.findAll();
		List<EtniaDto> dto = result.stream().map(x -> new EtniaDto(x)).toList();
		return dto;
	}
}