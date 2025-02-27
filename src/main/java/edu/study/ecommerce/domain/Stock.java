package edu.study.ecommerce.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock {

    Integer id;
    LocalDateTime dateCreated;
    Integer unitIn;
    Integer unitOut;
    String description;
    Integer balance;
    Product product;
}
