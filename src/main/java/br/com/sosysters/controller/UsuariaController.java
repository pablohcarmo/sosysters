package br.com.sosysters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sosysters.dao.UsuariaDao;
import br.com.sosysters.pojo.Usuaria;
import br.com.sosysters.repository.UsuariaRepository;

//@Controller
//@RequestMapping
@RestController
@RequestMapping("/api/usuarias")
public class UsuariaController {
	@Autowired
	private UsuariaDao dao;

	@Autowired
	private UsuariaRepository usuariaRepository;

	@GetMapping
	public List<Usuaria> listarUsuarias() {
		return dao.getLista();
	}

	@PostMapping
	public ResponseEntity<Usuaria> criarUsuaria(@RequestBody Usuaria usuaria) {
	    Usuaria nova = usuariaRepository.save(usuaria);
	    return ResponseEntity.status(HttpStatus.CREATED).body(nova);
	}


	/*private UsuariaDao dao = new UsuariaDao();

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
*/

}