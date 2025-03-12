package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.LogoutService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/close")
public class LogoutController {

    private final LogoutService logoutService;


    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }


    /**
     * Logs out the user by invalidating the session and redirects to the home page.
     *
     * @param httpSession the HTTP session to invalidate.
     * @return a redirect to the home page ("/home").
     */
    public String logout(HttpSession httpSession) {
        logoutService.logout(httpSession);
        return "redirect:/home";
    }
}
