package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.OrderProductRepository;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.OrderProduct;

import java.util.List;

public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }


        /**
     * Create a new OrderProduct
     * @param orderProduct the OrderProduct to create.
     * @return OrderProduct created.
     */
    public OrderProduct create (OrderProduct orderProduct) {
        return orderProductRepository.create(orderProduct);
    };

    /**
     * Get all OrderProducts
     * @return the list of OrderProducts.
     */
    public Iterable<OrderProduct> getOrderProducts() {
        return orderProductRepository.getOrderProducts();
    };

    /**
     * Get all OrderProducts for a specific Order.
     * @param order the order to search for.
     * @return the list of OrderProducts.
     */
    public List<OrderProduct> getOrderProductsByOrder(Order order) {
        return orderProductRepository.getOrderProductsByOrder(order);
    };

}
