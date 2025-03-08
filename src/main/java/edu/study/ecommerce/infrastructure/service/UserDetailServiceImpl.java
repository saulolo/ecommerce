package edu.study.ecommerce.infrastructure.service;

import edu.study.ecommerce.application.service.LoginService;
import edu.study.ecommerce.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final LoginService loginService;

    private final Integer USER_NOT_FOUND = 0;

    public UserDetailServiceImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Loads a user by their username for Spring Security authentication.
     * If the user is found, returns a UserDetails object with their credentials and roles.
     * If the user is not found, throws a UsernameNotFoundException.
     *
     * @param username the username of the user to load. Must not be null or empty.
     * @return a UserDetails object containing the user's credentials and roles.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Integer idUser = loginService.getUserId(username);
        if (idUser != USER_NOT_FOUND) {
            User user = loginService.getUser(username);
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getUserType().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("usuario no encontrado");
        }
    }
}
