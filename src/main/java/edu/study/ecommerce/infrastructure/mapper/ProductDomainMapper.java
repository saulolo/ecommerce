package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.infrastructure.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProductDomainMapper {

    private final UserDomainMapper userDomainMapper;

    public ProductDomainMapper(UserDomainMapper userDomainMapper) {
        this.userDomainMapper = userDomainMapper;
    }


    /**
     * Converts a ProductEntity to a Product
     *
     * @param productEntity
     * @return Product
     */
    public Product fromProductEntityToProduct(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return Product.builder()
                .id(productEntity.getId())
                .code(productEntity.getCode())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .price(productEntity.getPrice())
                .dateCreated(productEntity.getDateCreated())
                .dateUpdated(productEntity.getDateUpdated())
                .user(userDomainMapper.fromUserEntityToUser(productEntity.getUserEntity()))
                .build();
    }

    /**
     * Converts a Product to a ProductEntity
     *
     * @param product
     * @return ProductEntity
     */
    public ProductEntity fromProductToProductEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductEntity.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .dateCreated(product.getDateCreated())
                .dateUpdated(product.getDateUpdated())
                .userEntity(userDomainMapper.fromUserToUserEntity(product.getUser()))
                .build();
    }

    /**
     * Converts a list of ProductEntity to a list of Product
     *
     * @param productEntities
     * @return List<Product>
     */
    public List<Product> fromProductEntitiesToProducts(Iterable<ProductEntity> productEntities) {
        return productEntities == null ? null : StreamSupport.stream(productEntities.spliterator(), false)
                .map(this::fromProductEntityToProduct)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of Product to a list of ProductEntity
     *
     * @param products
     * @return List<ProductEntity>
     */
    public List<ProductEntity> fromProductsToProductEntities(Iterable<Product> products) {
        return products == null ? null : StreamSupport.stream(products.spliterator(), false)
                .map(this::fromProductToProductEntity)
                .collect(Collectors.toList());
    }

}
