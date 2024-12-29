package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.dto.UserDTO;

public class UserDTOMapper {


    /**
     * This method converts a User object to a UserDTO object
     * @param user
     * @return UserDTO
     */
    public UserDTO fromUserToUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .cellphone(user.getCellphone())
                .password(user.getPassword())
                .userType(user.getUserType())
                .dateCreated(user.getDateCreated())
                .build();
    }

    /**
     * This method converts a UserDTO object to a User object
     * @param userDTO
     * @return User
     */
    public User fromUserDtoToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .cellphone(userDTO.getCellphone())
                .password(userDTO.getPassword())
                .userType(userDTO.getUserType())
                .dateCreated(userDTO.getDateCreated())
                .build();
    }
}
