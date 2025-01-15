package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.OrderProduct;
import edu.study.ecommerce.infrastructure.entity.OrderProductEntity;
import edu.study.ecommerce.infrastructure.entity.OrderProductPK;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OrderProductMapper {

    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    public OrderProductMapper(ProductMapper productMapper, OrderMapper orderMapper) {
        this.productMapper = productMapper;
        this.orderMapper = orderMapper;
    }

    /**
     * Convert OrderProductEntity to OrderProduct
     * - Maps `pk.productEntity` to `product`
     * - Maps `quantity` to `quantity`
     * - Maps `pk.orderEntity` to `order`
     *
     * @param orderProductEntity the entity to convert
     * @return the converted OrderProduct object
     */
    public OrderProduct toOrderProduct(OrderProductEntity orderProductEntity) {
        if (orderProductEntity == null || orderProductEntity.getPk() == null) {
            return null;
        }

        return OrderProduct.builder()
                .product(productMapper.toProduct(orderProductEntity.getPk().getProductEntity()))
                .quantity(orderProductEntity.getQuantity())
                .order(orderMapper.toOrder(orderProductEntity.getPk().getOrderEntity()))
                .build();
    }

    /**
     * Convert Iterable<OrderProductEntity> to List<OrderProduct>
     *
     * @param orderProductEntities iterable of entities to convert
     * @return list of converted OrderProduct objects
     */
    public List<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities) {
        if (orderProductEntities == null) {
            return List.of();
        }

        return StreamSupport.stream(orderProductEntities.spliterator(), false)
                .map(this::toOrderProduct)
                .collect(Collectors.toList());
    }

    /**
     * Convert OrderProduct to OrderProductEntity
     * - Maps `product` to `pk.productEntity`
     * - Maps `quantity` to `quantity`
     * - Maps `order` to `pk.orderEntity`
     *
     * @param orderProduct the domain object to convert
     * @return the converted OrderProductEntity
     */
    public OrderProductEntity toOrderProductEntity(OrderProduct orderProduct) {
        if (orderProduct == null || orderProduct.getProduct() == null || orderProduct.getOrder() == null) {
            return null;
        }

        OrderProductPK pk = new OrderProductPK();
        pk.setOrderEntity(orderMapper.toOrderEntity(orderProduct.getOrder()));
        pk.setProductEntity(productMapper.toProductEntity(orderProduct.getProduct()));

        return OrderProductEntity.builder()
                .pk(pk)
                .quantity(orderProduct.getQuantity())
                .build();
    }

    /**
     * Convert Iterable<OrderProduct> to List<OrderProductEntity>
     *
     * @param orderProducts iterable of domain objects to convert
     * @return list of converted OrderProductEntities
     */
    public List<OrderProductEntity> toOrderProductEntities(Iterable<OrderProduct> orderProducts) {
        if (orderProducts == null) {
            return List.of();
        }

        return StreamSupport.stream(orderProducts.spliterator(), false)
                .map(this::toOrderProductEntity)
                .collect(Collectors.toList());
    }

}
