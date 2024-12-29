package edu.study.ecommerce.infrastructure.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    Integer id;
    String code;
    String name;
    String description;
    String image;
    BigDecimal price;

    LocalDateTime dateCreated;
    LocalDateTime dateUpdated;

    UserDTO user;
}
