package br.com.sosysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dto.EtniaDto;
import br.com.sosysters.services.EtniaService;

@RestController
@RequestMapping (value = "api")
public class EtniaController {
	@Autowired
	private EtniaService etniaService;

	@GetMapping (value = "etnias")
	public List<EtniaDto> listEtnias(){
		List<EtniaDto> result = etniaService.listEtnias();
		return result;
	}
}