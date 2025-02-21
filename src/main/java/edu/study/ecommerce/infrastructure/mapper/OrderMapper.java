package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.infrastructure.entity.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OrderMapper {

    private final UserDomainMapper userDomainMapper;

    public OrderMapper(UserDomainMapper userDomainMapper) {
        this.userDomainMapper = userDomainMapper;
    }


    /**
     * Convert OrderEntity to Order
     *
     * @param orderEntity the entity to convert
     * @return the converted domain object
     */
    public Order toOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        return Order.builder()
                .id(orderEntity.getId())
                .dateCreated(orderEntity.getDateCreated())
                .user(orderEntity.getUserEntity() != null
                        ? userDomainMapper.fromUserEntityToUser(orderEntity.getUserEntity())
                        : null)
                .build();
    }

    /**
     * Convert Iterable<OrderEntity> to List<Order>
     *
     * @param orderEntities iterable of entities to convert
     * @return list of converted domain objects
     */
    public List<Order> toOrders(Iterable<OrderEntity> orderEntities) {
        if (orderEntities == null) {
            return List.of();
        }

        return StreamSupport.stream(orderEntities.spliterator(), false)
                .map(this::toOrder)
                .collect(Collectors.toList());
    }

    /**
     * Convert Order to OrderEntity
     *
     * @param order the domain object to convert
     * @return the converted entity
     */
    public OrderEntity toOrderEntity(Order order) {
        if (order == null) {
            return null;
        }

        // Explicit mapping of fields
        return OrderEntity.builder()
                .id(order.getId())
                .dateCreated(order.getDateCreated())
                .userEntity(order.getUser() != null
                        ? userDomainMapper.fromUserToUserEntity(order.getUser())
                        : null)
                .build();
    }

    /**
     * Convert Iterable<Order> to List<OrderEntity>
     *
     * @param orders iterable of domain objects to convert
     * @return list of converted entities
     */
    public List<OrderEntity> toOrderEntities(Iterable<Order> orders) {
        if (orders == null) {
            return List.of();
        }

        return StreamSupport.stream(orders.spliterator(), false)
                .map(this::toOrderEntity)
                .collect(Collectors.toList());
    }

}
