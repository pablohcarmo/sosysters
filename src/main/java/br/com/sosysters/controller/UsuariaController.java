package br.com.sosysters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sosysters.dao.UsuariaDao;
import br.com.sosysters.pojo.Usuaria;

@Controller
@RequestMapping

public class UsuariaController {
	private UsuariaDao dao = new UsuariaDao();

	@GetMapping
	public String showUsuarias() {
		return "usuarias";
	}

	@GetMapping("/new")
	public String showInsertForm(Model model) {
		model.addAttribute("usuaria", new Usuaria());
		return "create-usuaria";
	}

	@PostMapping("/new")
	public String createUsuaria(@ModelAttribute Usuaria usuaria) {
		dao.insert(usuaria);
		return "redirect:/usuarias";
	}
}