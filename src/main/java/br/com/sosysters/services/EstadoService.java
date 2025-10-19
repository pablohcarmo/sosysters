package br.com.sosysters.services;

import br.com.sosysters.dto.EstadoDto;
import br.com.sosysters.entities.Estado;
import br.com.sosysters.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	public List<EstadoDto> listEstados() {
		List<Estado> result = estadoRepository.findAll();
		List<EstadoDto> dto = result.stream().map(x -> new EstadoDto(x)).toList();
		return dto;
	}
}