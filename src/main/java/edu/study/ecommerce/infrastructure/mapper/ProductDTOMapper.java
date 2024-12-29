package edu.study.ecommerce.infrastructure.mapper;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.infrastructure.dto.ProductDTO;

public class ProductDTOMapper {

    private final UserDTOMapper userDTOMapper;

    public ProductDTOMapper(UserDTOMapper userDTOMapper) {
        this.userDTOMapper = userDTOMapper;
    }


    /**
     * This method converts a Product object to a ProductDTO object
     * @param product
     * @return ProductDTO
     */
    public ProductDTO fromProductToProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .dateCreated(product.getDateCreated())
                .dateUpdated(product.getDateUpdated())
                .user(userDTOMapper.fromUserToUserDTO(product.getUser()))
                .build();
    }

    /**
     * This method converts a ProductDTO object to a Product object
     * @param productDTO
     * @return Product
     */
    public Product fromProductDTOtoProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return Product.builder()
                .id(productDTO.getId())
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .image(productDTO.getImage())
                .price(productDTO.getPrice())
                .dateCreated(productDTO.getDateCreated())
                .dateUpdated(productDTO.getDateUpdated())
                .user(userDTOMapper.fromUserDtoToUser(productDTO.getUser()))
                .build();
    }
}
