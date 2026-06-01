package br.com.sosysters.controllers;

import br.com.sosysters.dto.MeuCadastroDto;
import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.EtniaService;
import br.com.sosysters.services.GeneroService;
import br.com.sosysters.services.UsuariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/meu-cadastro")
public class MeuCadastroController {

    @Autowired
    private UsuariaService usuariaService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private EtniaService etniaService;

    @GetMapping
    public String loadMeuCadastroPage(@AuthenticationPrincipal Usuaria usuaria, Model model) {
        carregarLists(model);
        model.addAttribute("cadastro", usuariaService.carregarMeuCadastro(usuaria.getUsername()));
        return "meu-cadastro";
    }

    @PostMapping
    public String salvarMeuCadastro(@AuthenticationPrincipal Usuaria usuaria,
                                    MeuCadastroDto dto,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        try {
            usuariaService.atualizarMeuCadastro(usuaria.getUsername(), dto);
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro atualizado com sucesso.");
            return "redirect:/perfil";
        } catch (Exception e) {
            carregarLists(model);
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("cadastro", dto);
            return "meu-cadastro";
        }
    }

    private void carregarLists(Model model) {
        model.addAttribute("generos", generoService.listGeneros());
        model.addAttribute("etnias", etniaService.listEtnias());
    }
}


