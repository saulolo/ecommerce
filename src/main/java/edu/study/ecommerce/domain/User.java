package edu.study.ecommerce.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Integer id;
    String username;
    String firstName;
    String lastName;
    String email;
    String address;
    String cellphone;
    String password;

    UserType userType;

    LocalDateTime dateCreated;

}
