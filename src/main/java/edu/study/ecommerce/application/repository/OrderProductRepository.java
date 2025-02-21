package edu.study.ecommerce.application.repository;

import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {


    /**
     * Create a new OrderProduct
     * @param orderProduct
     * @return
     */
    OrderProduct create (OrderProduct orderProduct);

    /**
     * Get all OrderProducts
     * @return
     */
    Iterable<OrderProduct> getOrderProducts();

    /**
     * Get all OrderProducts for a specific Order.
     * @param order the order to search for.
     * @return the list of OrderProducts.
     */
    List<OrderProduct> getOrderProductsByOrder(Order order);

}
