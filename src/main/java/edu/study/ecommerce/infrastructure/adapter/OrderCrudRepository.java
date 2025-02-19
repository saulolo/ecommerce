package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCrudRepository extends JpaRepository<OrderEntity, Integer > {

}
