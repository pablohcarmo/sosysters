package br.com.sosysters.controllers;

import br.com.sosysters.dto.EstadoDto;
import br.com.sosysters.dto.EtniaDto;
import br.com.sosysters.services.EstadoService;
import br.com.sosysters.services.EtniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "api")
public class EstadoController {
	@Autowired
	private EstadoService estadoService;

	@GetMapping (value = "estados")
	public List<EstadoDto> listEstados(){
		List<EstadoDto> result = estadoService.listEstados();
		return result;
	}
}