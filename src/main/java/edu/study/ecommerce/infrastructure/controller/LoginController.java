package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.LoginService;
import edu.study.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
     * Handles GET requests for user access based on their session.
     * This method retrieves the user from the session and redirects them based on their role (ADMIN or regular user).
     * If the user does not exist, it redirects to the home page.
     *
     * @param httpSession the HTTP session containing the user's ID.
     * @return a redirect to the admin page if the user is an ADMIN, or to the home page otherwise.
     */
    @GetMapping("/access")
    public String access(HttpSession httpSession) {
        User user = loginService.getUser(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        if (loginService.existUser(user.getEmail())) {
           if (loginService.getUserType(user.getEmail()).name().equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        }
        return "redirect:/home";
    }
}
