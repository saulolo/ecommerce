package edu.study.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDateTime dateCreated;
    Integer unitIn;
    Integer unitOut;
    String description;
    Integer balance;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    ProductEntity productEntity;

}
