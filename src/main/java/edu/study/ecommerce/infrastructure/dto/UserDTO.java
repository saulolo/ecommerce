package edu.study.ecommerce.infrastructure.dto;

import edu.study.ecommerce.domain.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

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
