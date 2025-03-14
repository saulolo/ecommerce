package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.OrderRepository;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.mapper.OrderMapper;
import edu.study.ecommerce.infrastructure.mapper.UserDomainMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderCrudRepository orderCrudRepository;
    private final OrderMapper orderMapper;
    private final UserDomainMapper userDomainMapper;

    public OrderRepositoryImpl(OrderCrudRepository orderCrudRepository, OrderMapper orderMapper, UserDomainMapper userDomainMapper) {
        this.orderCrudRepository = orderCrudRepository;
        this.orderMapper = orderMapper;
        this.userDomainMapper = userDomainMapper;
    }


    /**
     * Get all orders
     *
     * @return Iterable<Order>
     */
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
        return orderMapper.toOrders(orderCrudRepository.findByUser(userDomainMapper.fromUserToUserEntity(user)));
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

    /**
     * Deletes an order by its ID.
     * Currently, this method is not implemented.
     *
     * @param id the ID of the order to delete.
     */
    @Override
    public void deleteOrderById(Integer id) {
    }
}
