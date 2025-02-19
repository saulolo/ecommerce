package edu.study.ecommerce.application.repository;

import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.User;

public interface OrderRepository {


    /**
     * Get all orders
     *
     * @return Iterable<Order>
     */
    Iterable<Order> getOrders();

    Iterable<Order> getOrdersByUser(User user);

    Order getOrderById(Integer id);

    /**
     * Save order
     *
     * @param order
     * @return Order
     */
    Order saveOrder(Order order);

    void deleteOrderById(Integer id);
}
