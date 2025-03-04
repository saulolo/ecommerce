package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.RegistrationService;
import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.domain.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/register")
public class RegistrationController {


    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    /**
     * Handles GET requests for the registration page.
     *
     * @return The name of the view "register".
     */
    @GetMapping
    public String register() {
        return "register";
    }

    /**
     * Handles POST requests for the registration page.
     * This method is called when a user submits the registration form.
     * It creates a new user object and delegates the registration to the registration service.
     *
     * @param user the user object to be registered. Must not be null.
     * @return The name of the view "register".
     */
    @PostMapping
    public String registerUser(User user) {
        user.setDateCreated(LocalDateTime.now());
        user.setUserType(UserType.USER);
        user.setUsername(user.getEmail());
        registrationService.register(user);
        return "redirect:/register";
    }
}
