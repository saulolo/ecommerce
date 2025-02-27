package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.UserRepository;
import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.mapper.UserDomainMapper;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserCrudRepository userCrudRepository;
    private final UserDomainMapper userDomainMapper;

    public UserRepositoryImpl(UserCrudRepository userCrudRepository, UserDomainMapper userDomainMapper) {
        this.userCrudRepository = userCrudRepository;
        this.userDomainMapper = userDomainMapper;
    }

    /**
     * Creates a new user by saving the user entity to the repository and mapping it back to a User object.
     *
     * @param user the user object to be created.
     * @return the created user.
     */
    @Override
    public User createUser(User user) {
        return userDomainMapper.fromUserEntityToUser(userCrudRepository.save(userDomainMapper.fromUserToUserEntity(user)));
    }

    /**
     * Finds a user by their email address.
     * Throws an exception if the user is not found.
     *
     * @param email the email address of the user to find.
     * @return the User object corresponding to the given email.
     * @throws NoSuchElementException if the user is not found.
     */
    @Override
    public User findByEmail(String email) {
        return userDomainMapper.fromUserEntityToUser(userCrudRepository.findByEmail(email).orElseThrow());
    }

    /**
     * Finds a user by their ID.
     * Throws an exception if the user is not found.
     *
     * @param id the ID of the user to find.
     * @return the User object corresponding to the given ID.
     * @throws NoSuchElementException if the user is not found.
     */
    @Override
    public User findById(Integer id) {
        return userDomainMapper.fromUserEntityToUser(userCrudRepository.findById(id).orElseThrow());
    }
}
