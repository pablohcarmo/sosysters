package br.com.sosysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dto.UsuariaDto;
import br.com.sosysters.services.UsuariaService;

@RestController
@RequestMapping (value = "usuarias")
public class UsuariaController {
	@Autowired
	private UsuariaService usuariaService;

	@GetMapping
	public List<UsuariaDto> findAll(){
		List<UsuariaDto> result = usuariaService.findAll();
		return result;
	}
}