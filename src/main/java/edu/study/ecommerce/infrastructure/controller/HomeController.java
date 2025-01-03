package edu.study.ecommerce.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * Home page
     * @return home page
     */
    @GetMapping
    public String home() {
        return "home";
    }
}
