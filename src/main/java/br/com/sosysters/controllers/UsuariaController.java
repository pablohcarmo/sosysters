package br.com.sosysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dto.NovaUsuariaDto;
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

	@PostMapping
	public ResponseEntity<Void> cadastrarUsuaria(@RequestBody NovaUsuariaDto dto) {
		usuariaService.cadastrarUsuaria(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}