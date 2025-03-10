package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * This method is used to register a new user
     *
     * @param user the user to register. Must not be null.
     */
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
    }
}
