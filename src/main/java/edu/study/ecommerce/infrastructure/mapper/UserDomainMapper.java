package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserDomainMapper {

    /**
     * Converts a UserEntity to a User
     *
     * @param userEntity
     * @return User
     */
    public User fromUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .cellphone(userEntity.getCellphone())
                .password(userEntity.getPassword())
                .userType(userEntity.getUserType())
                .dateCreated(userEntity.getDateCreated())
                .build();
    }

    /**
     * Converts a User to a UserEntity
     *
     * @param user
     * @return UserEntity
     */
    public UserEntity fromUserToUserEntity(User user) {
        if (user == null) {
            return null;
        }
        return UserEntity.builder()
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
     * Converts a list of UserEntity to a list of User
     *
     * @param userEntities
     * @return List<User>
     */
    public List<User> fromUserEntitiesToUsers(List<UserEntity> userEntities) {
        return userEntities == null ? null : userEntities.stream()
                .map(this::fromUserEntityToUser)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of User to a list of UserEntity
     *
     * @param users
     * @return List<UserEntity>
     */
    public List<UserEntity> fromUsersToUserEntities(List<User> users) {
        return users == null ? null : users.stream()
                .map(this::fromUserToUserEntity)
                .collect(Collectors.toList());
    }

}
