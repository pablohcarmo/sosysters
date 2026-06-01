package br.com.sosysters.controllers;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/nova-solicitacao")
public class NovaSolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public String loadNovaSolicitacaoPage() {
        return "nova-solicitacao";
    }

    @PostMapping
    public String criarSolicitacao(@AuthenticationPrincipal Usuaria usuaria,
                                   @RequestParam("servico") String servico,
                                   @RequestParam("problema") String problema,
                                   RedirectAttributes redirectAttributes) {
        if (servico == null || servico.isBlank()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Selecione um serviço para continuar.");
            return "redirect:/nova-solicitacao";
        }

        if (problema == null || problema.isBlank()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Descreva o trabalho antes de enviar.");
            return "redirect:/nova-solicitacao";
        }

        solicitacaoService.criarSolicitacao(usuaria.getUsername(), servico, problema.trim());
        redirectAttributes.addFlashAttribute("mensagem", "Solicitação criada com sucesso! Ela já aparece na pesquisa.");
        return "redirect:/pesquisa";
    }
}