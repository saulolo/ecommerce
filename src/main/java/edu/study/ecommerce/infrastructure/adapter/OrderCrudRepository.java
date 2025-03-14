package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.OrderEntity;
import edu.study.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCrudRepository extends JpaRepository<OrderEntity, Integer > {

    /**
     * Find orders by user entity
     *
     * @param userEntity
     * @return Iterable<OrderEntity>
     */
    Iterable<OrderEntity> findByUserEntity(UserEntity userEntity);
}
