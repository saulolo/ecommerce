package edu.study.ecommerce.application.service;

import jakarta.servlet.http.HttpSession;

public class LogoutService {


    /**
     * This method is used to logout the user.
     * It removes the iduser attribute from the session.
     *
     * @param httpSession the HttpSession object to logout
     */
    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute("iduser");
    }
}
