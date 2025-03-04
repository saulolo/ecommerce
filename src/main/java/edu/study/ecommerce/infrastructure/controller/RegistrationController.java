package edu.study.ecommerce.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    /**
     * Handles GET requests for the registration page.
     *
     * @return The name of the view "register".
     */
    @GetMapping
    public String register() {
        return "register";
    }
}
