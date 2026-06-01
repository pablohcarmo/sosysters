package br.com.sosysters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dto.NovaUsuariaDto;
import br.com.sosysters.services.UsuariaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping ("/usuarias")
public class UsuariaController {
	@Autowired
	private UsuariaService usuariaService;

	private static final Logger logger = LoggerFactory.getLogger(UsuariaController.class);


	@PostMapping
	public ResponseEntity<?> cadastrarUsuaria(@RequestBody NovaUsuariaDto dto, HttpServletRequest request) {
		logger.info("Recebendo cadastro de usuária: {} - {}", dto.getNomeUsuaria(), dto.getEmailUsuaria());
		try {
			usuariaService.cadastrarUsuaria(dto, request);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.error("Erro ao cadastrar usuária", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar: " + e.getMessage());
		}
	}
}