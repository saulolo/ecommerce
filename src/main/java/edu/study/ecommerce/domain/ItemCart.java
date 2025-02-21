package edu.study.ecommerce.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCart {

    Integer idProduct;
    String nameProduct;
    Integer quantity;
    BigDecimal price;

    /**
     * Calculates the total price for the item (price * quantity).
     * @return the total price as a BigDecimal.
     */
    public BigDecimal getTotalPriceItem() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}
