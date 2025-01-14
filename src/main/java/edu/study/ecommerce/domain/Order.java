package edu.study.ecommerce.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Integer id;
    LocalDateTime dateCreated;
    List<OrderProduct> orderProducts;
    User user;

    /**
     * Add a list of products to the order
     *
     * @param orderProducts list of products to add
     */
    public void addOrdersProduct(List<OrderProduct> orderProducts) {
        this.setOrderProducts(orderProducts);
    }

    /**
     * Calculate the total price of the order
     *
     * @return total price of the order
     */
    public BigDecimal getTotalOrderPrice() {
        return getOrderProducts().stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
