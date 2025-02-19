package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.OrderRepository;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderCrudRepository orderCrudRepository;
    private final OrderMapper orderMapper;

    public OrderRepositoryImpl(OrderCrudRepository orderCrudRepository, OrderMapper orderMapper) {
        this.orderCrudRepository = orderCrudRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Iterable<Order> getOrders() {
        return null;
    }


    /**
     * Get orders by user
     *
     * @param user
     * @return Iterable<Order>
     */
    @Override
    public Iterable<Order> getOrdersByUser(User user) {
        return orderMapper.toOrders(orderCrudRepository.findAll());
    }

    @Override
    public Order getOrderById(Integer id) {
        return null;
    }

    /**
     * Save order
     *
     * @param order
     * @return Order
     */
    @Override
    public Order saveOrder(Order order) {
        return orderMapper.toOrder(orderCrudRepository.save(orderMapper.toOrderEntity(order)));
    }

    @Override
    public void deleteOrderById(Integer id) {

    }
}
