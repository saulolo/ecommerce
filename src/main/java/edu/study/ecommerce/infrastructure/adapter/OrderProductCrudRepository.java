package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.OrderEntity;
import edu.study.ecommerce.infrastructure.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductCrudRepository extends JpaRepository<OrderProductEntity, Integer > {

    /**
     * Find all OrderProductEntity by OrderEntity
     * @param orderEntity the order to search for.
     * @return the list of OrderProductEntity.
     */
    List<OrderProductEntity> findByPkOrderEntity(OrderEntity orderEntity);

}
