package edu.study.ecommerce.infrastructure.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordersProducts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProductEntity {

    @EmbeddedId
    OrderProductPK pk;
    Integer quantity;

}
