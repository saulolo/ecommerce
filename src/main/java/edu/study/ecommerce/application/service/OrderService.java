package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.OrderRepository;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.User;

public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Save order
     *
     * @param order
     * @return Order
     */
    public Order saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    };


    /**
     * Get orders by user
     *
     * @param user User object to get orders by user from repository
     * @return Iterable<Order> List of orders
     */
    public Iterable<Order> getOrdersByUsers(User user) {
        return orderRepository.getOrders();
    };
}
