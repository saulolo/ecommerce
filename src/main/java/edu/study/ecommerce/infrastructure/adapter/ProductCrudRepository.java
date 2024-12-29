package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.ProductEntity;
import edu.study.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Integer> {

    /**
     * Find all products by user entity
     *
     * @param userEntity
     * @return Iterable<ProductEntity>
     */
    Iterable<ProductEntity> findByUserEntity(UserEntity userEntity);
}
