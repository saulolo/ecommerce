package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.domain.UserType;

public class LoginService {


    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }



    public boolean existUser(String email) {
        try {
            User user = userService.findByEmail(email);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * This method is used to check if the user exists in the database
     *
     * @param email the email address of the user to search for. Must not be null or empty.
     * @return true if the user exists, false otherwise.
     * @throws IllegalArgumentException if the email is null or empty.
     */
    public Integer getUserId(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El Email no debe de ser nulo ni vacio.");
        }
        try {
            return userService.findByEmail(email).getId();
        } catch (Exception e) {
            return 0;
        }
    }


    public UserType getUserType(String email) {
            return userService.findByEmail(email).getUserType();
    }

    /**
     * Retrieves the user associated with the given email address.
     * If the user is not found or an exception occurs, returns a new, empty User object.
     *
     * @param email the email address of the user to search for. Must not be null or empty.
     * @return the User object if found, or a new, empty User object otherwise.
     * @throws IllegalArgumentException if the email is null or empty.
     */
    public User getUser(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El Email no debe de ser nulo ni vacio.");
        }

        try {
            return userService.findByEmail(email);
        } catch (Exception e) {
            return new User();
        }
    }

    /**
     * Retrieves the user associated with the given id.
     * If the user is not found or an exception occurs, returns a new, empty User object.
     *
     * @param id the id of the user to search for. Must not be null.
     * @return the User object if found, or a new, empty User object otherwise.
     * @throws IllegalArgumentException if the id is null.
     */
    public User getUser(Integer id) {
        try {
            return userService.findById(id);
        } catch (Exception e) {
            return new User();
        }
    }
}
