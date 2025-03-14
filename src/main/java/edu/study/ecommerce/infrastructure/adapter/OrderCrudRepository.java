package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.OrderEntity;
import edu.study.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCrudRepository extends JpaRepository<OrderEntity, Integer > {

    /**
     * Find all orders by user
     * @param userEntity
     * @return
     */
    public Iterable<OrderEntity> findByUser(UserEntity userEntity);
}
