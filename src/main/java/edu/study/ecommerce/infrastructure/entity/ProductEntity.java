package edu.study.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String code;
    String name;
    String description;
    String image;
    BigDecimal price;

    LocalDateTime dateCreated;
    LocalDateTime dateUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity userEntity;

}
