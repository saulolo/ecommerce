package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.OrderRepository;
import edu.study.ecommerce.domain.Order;

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
     * Get all orders
     *
     * @return Iterable<Order>
     */
    public Iterable<Order> getOrders() {
        return orderRepository.getOrders();
    };
}
