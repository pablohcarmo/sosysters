package br.com.sosysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dto.GeneroDto;
import br.com.sosysters.services.GeneroService;

@RestController
@RequestMapping (value = "api")
public class GeneroController {
	@Autowired
	private GeneroService generoService;

	@GetMapping (value = "generos")
	public List<GeneroDto> listGeneros(){
		List<GeneroDto> result = generoService.listGeneros();
		return result;
	}
}