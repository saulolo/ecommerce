package edu.study.ecommerce.infrastructure.dto;

import edu.study.ecommerce.domain.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Nombre es requerido")
    String firstName;

    @NotBlank(message = "Apellido es requerido")
    String lastName;

    @Email(message = "Debe de ingresar un email válido")
    String email;

    @NotBlank(message = "Dirección es requerido")
    String address;

    @NotBlank(message = "Celular es requerido")
    String cellphone;

    @NotBlank(message = "Clave es requerido")
    String password;

    UserType userType;

    LocalDateTime dateCreated;
}
