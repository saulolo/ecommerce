package edu.study.ecommerce.infrastructure.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProductPK {

    @ManyToOne(fetch = FetchType.LAZY)
    OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    ProductEntity productEntity;

}
