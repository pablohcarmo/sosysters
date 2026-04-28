package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feed")
public class FeedController {
    
    @GetMapping
    public String loadFeedPage() {
        return "feed/feed";
    }
}