package br.com.sosysters.controllers;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.entities.Profissao;
import br.com.sosysters.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profissoes")
public class ProfissaoController {

	@Autowired
	private ProfissaoService profissaoService;

	@GetMapping("/gerenciar")
	public String gerenciarProfissoes(@AuthenticationPrincipal Usuaria usuaria, Model model) {
		model.addAttribute("todasProfissoes", profissaoService.listarTodas());
		Profissao profissaoAtual = profissaoService.carregarProfissao(usuaria.getUsername());
		java.util.Set<Long> profissoesEscolhidas = profissaoAtual != null 
			? java.util.Set.of(profissaoAtual.getIdProfissao()) 
			: java.util.Collections.emptySet();
		model.addAttribute("profissoesEscolhidas", profissoesEscolhidas);
		return "gerenciar-profissoes";
	}

	@PostMapping("/salvar")
	public String salvarProfissoes(@AuthenticationPrincipal Usuaria usuaria,
			@RequestParam(name = "profissao", required = false) String profissaoIdStr,
			RedirectAttributes redirectAttributes) {

		try {
			Long idProfissao = (profissaoIdStr != null && !profissaoIdStr.isEmpty())
					? Long.parseLong(profissaoIdStr)
					: null;

			profissaoService.salvarProfissao(usuaria.getUsername(), idProfissao);
			redirectAttributes.addFlashAttribute("mensagem", "Profissão atualizada com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar a profissão. Tente novamente.");
		}

		return "redirect:/perfil";
	}
}

