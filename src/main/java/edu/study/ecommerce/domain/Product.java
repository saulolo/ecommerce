package edu.study.ecommerce.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    Integer id;
    String code;
    String name;
    String description;
    String image;
    BigDecimal price;

    LocalDateTime dateCreated;
    LocalDateTime dateUpdated;

    User user;

    public Product() {
        this.setCode(UUID.randomUUID().toString());
    }
}
