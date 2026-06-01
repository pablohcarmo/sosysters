package br.com.sosysters.controllers;

import java.util.Map;

import br.com.sosysters.dto.TrocarSenhaDto;
import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.UsuariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioApiController {

    private final UsuariaService usuariaService;

    public UsuarioApiController(UsuariaService usuariaService) {
        this.usuariaService = usuariaService;
    }

    @PostMapping("/trocar-senha")
    public ResponseEntity<?> trocarSenha(@AuthenticationPrincipal Usuaria usuaria,
                                         @RequestBody TrocarSenhaDto dto) {
        usuariaService.trocarSenha(usuaria.getEmailUsuaria(), dto);
        return ResponseEntity.ok(Map.of("message", "Senha alterada com sucesso."));
    }
}

