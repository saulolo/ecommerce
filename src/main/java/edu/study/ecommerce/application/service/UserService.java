package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.UserRepository;
import edu.study.ecommerce.domain.User;

public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    /**
     * Creates a new user by delegating the operation to the user repository.
     *
     * @param user the user object to be created.
     * @return the created user.
     */
    public User createUser(User user) {
        return userRepository.createUser(user);
    };

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find.
     * @return the User object corresponding to the given email, or null if not found.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    };

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find.
     * @return the User object corresponding to the given ID, or null if not found.
     */
    public User findById(Integer id) {
        return userRepository.findById(id);
    };

}
