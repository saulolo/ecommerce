package edu.study.ecommerce.infrastructure.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * Redirects the user to the appropriate page based on their role.
     *
     * This method is called when the user successfully logs in.
     * It retrieves the user's role and redirects them to the appropriate page.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param authentication the authentication object containing the user's details
     * @throws IOException if an I/O error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AtomicReference<String> redirectURL = new AtomicReference<>(request.getContextPath());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> {
                    if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                        redirectURL.set("/admin");
                    } else {
                        redirectURL.set("/home");
                    }
                }
        );
        response.sendRedirect(String.valueOf(redirectURL));
    }
}
