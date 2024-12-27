package edu.study.ecommerce.infrastructure.entity;

import edu.study.ecommerce.domain.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String firstName;
    String lastName;
    String email;
    String address;
    String cellphone;
    String password;

    @Enumerated(EnumType.STRING)
    UserType userType;

    LocalDateTime dateCreated;

}
