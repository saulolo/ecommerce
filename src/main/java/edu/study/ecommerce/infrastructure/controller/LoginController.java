package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.LoginService;
import edu.study.ecommerce.infrastructure.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    /**
     * This method is used to return the login page
     * @return login page
     */
    @GetMapping
    public String login() {
        return "login";
    }


    /**
     * Handles POST requests for user login.
     * This method authenticates the user and redirects them based on their role (ADMIN or regular user).
     * If the user does not exist, it redirects to the home page.
     *
     * @param userDTO the UserDTO object containing the user's credentials (username and password).
     * @param httpSession the HTTP session to store user-specific data (e.g., user ID).
     * @return a redirect to the admin page if the user is an ADMIN, or to the home page otherwise.
     */
    @PostMapping
    public String access(UserDTO userDTO, HttpSession httpSession) {
        userDTO.setEmail(userDTO.getUsername());
        log.info("UserDTO email: {}", userDTO.getEmail());
        log.info("UserDTO password: {}", userDTO.getPassword());
        if (loginService.existUser(userDTO)) {
            httpSession.setAttribute("iduser", loginService.getUserId(userDTO.getEmail()));
            if (loginService.getUserType(userDTO).name().equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        }
        return "redirect:/home";
    }
}
