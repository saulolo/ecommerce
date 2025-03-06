package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.domain.UserType;
import edu.study.ecommerce.infrastructure.dto.UserDTO;

public class LoginService {


    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }


    /**
     * This method is used to check if the user exists in the database
     *
     * @param userDTO the user object to be checked.
     * @return true if the user exists, false otherwise.
     */
    public boolean existUser(UserDTO userDTO) {
        try {
            User user = userService.findByEmail(userDTO.getEmail());
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

    /**
     * Retrieves the user type associated with the given email address.
     * This method queries the user service to find the user by email and returns their user type.
     *
     * @param userDTO the user object to be checked.
     * @return the user type of the user.
     */
    public UserType getUserType(UserDTO userDTO) {
            return userService.findByEmail(userDTO.getEmail()).getUserType();
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
}
