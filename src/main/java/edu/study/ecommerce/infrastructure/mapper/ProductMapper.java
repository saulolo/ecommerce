package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.infrastructure.entity.ProductEntity;

public class ProductMapper {


        /**
         * Convert ProductEntity to Product
         *
         * @param productEntity
         * @return
         */
        public Product toProduct(ProductEntity productEntity) {
            if (productEntity == null) {
                return null;
            }

            return Product.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .price(productEntity.getPrice())
                    .build();
        }

        /**
         * Convert Product to ProductEntity
         *
         * @param product
         * @return
         */
        public ProductEntity toProductEntity(Product product) {
            if (product == null) {
                return null;
            }

            return ProductEntity.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();
        }
}
