package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.User;

public class RegistrationService {

    private final UserService userService;


    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user by delegating the creation to the user service.
     * This method calls the `createUser` method of the `userService` to persist the user.
     *
     * @param user the user object to be registered. Must not be null.
     * @throws IllegalArgumentException if the user parameter is null.
     */
    public void register(User user) {
        userService.createUser(user);
    }
}
