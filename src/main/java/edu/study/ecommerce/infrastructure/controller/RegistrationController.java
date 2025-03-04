package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.RegistrationService;
import edu.study.ecommerce.infrastructure.dto.UserDTO;
import edu.study.ecommerce.infrastructure.mapper.UserDTOMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {


    private final RegistrationService registrationService;
    private final View error;
    private final UserDTOMapper userDTOMapper;

    public RegistrationController(RegistrationService registrationService, View error, UserDTOMapper userDTOMapper) {
        this.registrationService = registrationService;
        this.error = error;
        this.userDTOMapper = userDTOMapper;
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
     *
     * @param userDTO The user to be registered.
     * @param bindingResult The result of the validation.
     * @return The name of the view "register".
     */
    @PostMapping
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> log.error("Errors {}", e.getDefaultMessage()));
        }
        registrationService.register(userDTOMapper.fromUserDtoToUser(userDTO));
        return "redirect:/register";
    }
}
