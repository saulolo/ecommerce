package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Integer> {
}
