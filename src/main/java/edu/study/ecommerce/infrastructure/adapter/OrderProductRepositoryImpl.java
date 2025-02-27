package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.OrderProductRepository;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.OrderProduct;
import edu.study.ecommerce.infrastructure.mapper.OrderMapper;
import edu.study.ecommerce.infrastructure.mapper.OrderProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderProductCrudRepository productCrudRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

    public OrderProductRepositoryImpl(OrderProductCrudRepository productCrudRepository, OrderMapper orderMapper, OrderProductMapper orderProductMapper) {
        this.productCrudRepository = productCrudRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
    }


    /**
     * Create a new OrderProduct
     * @param orderProduct the OrderProduct to create.
     * @return OrderProduct created.
     */
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductMapper.toOrderProduct(productCrudRepository.save(orderProductMapper.toOrderProductEntity(orderProduct)));
    }

    /**
     * Get all OrderProducts
     * @return the list of OrderProducts.
     */
    @Override
    public Iterable<OrderProduct> getOrderProducts() {
        return orderProductMapper.toOrderProducts(productCrudRepository.findAll());
    }

    /**
     * Get all OrderProducts for a specific Order.
     * @param order the order to search for.
     * @return the list of OrderProducts.
     */
    @Override
    public List<OrderProduct> getOrderProductsByOrder(Order order) {
        return orderProductMapper.toOrderProducts(productCrudRepository.findByPkOrderEntity(orderMapper.toOrderEntity(order)));
    }
}
