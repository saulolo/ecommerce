package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<UserEntity, Integer > {

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find.
     * @return an Optional containing the UserEntity if found, or an empty Optional otherwise.
     */
    Optional<UserEntity> findByEmail(String email);

}
