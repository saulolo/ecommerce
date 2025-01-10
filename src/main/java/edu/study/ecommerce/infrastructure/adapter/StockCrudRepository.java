package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.infrastructure.entity.ProductEntity;
import edu.study.ecommerce.infrastructure.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCrudRepository extends JpaRepository<StockEntity, Integer> {


    /**
     * Find stock by product entity
     * @param productEntity
     * @return List<StockEntity>
     */
    List<StockEntity> findByProductEntity(ProductEntity productEntity);
}
