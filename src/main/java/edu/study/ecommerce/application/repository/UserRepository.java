package edu.study.ecommerce.application.repository;

import edu.study.ecommerce.domain.User;

public interface UserRepository {


    /**
     * Creates a new user.
     *
     * @param user the user object to be created.
     * @return the created user.
     */
    User createUser(User user);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find.
     * @return the User object corresponding to the given email, or null if not found.
     */
    User findByEmail(String email);

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user to find.
     * @return the User object corresponding to the given ID, or null if not found.
     */
    User findById(Integer id);

}
