package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home", "/index"})
public class IndexController {
    @GetMapping
    public String loadIndexPage() {
        return "index";
    }
}
