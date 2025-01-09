package edu.study.ecommerce.infrastructure.configuration;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.application.service.ProductService;
import edu.study.ecommerce.application.service.UploadFile;
import edu.study.ecommerce.infrastructure.mapper.ProductDTOMapper;
import edu.study.ecommerce.infrastructure.mapper.ProductDomainMapper;
import edu.study.ecommerce.infrastructure.mapper.UserDTOMapper;
import edu.study.ecommerce.infrastructure.mapper.UserDomainMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * ProductService bean
     * @param productRepository
     * @return ProductService
     */
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    /**
     * ProductDomainMapper bean
     * @return ProductDomainMapper
     */
    @Bean
    public ProductDomainMapper productDomainMapper(UserDomainMapper userDomainMapper) {
        return new ProductDomainMapper(userDomainMapper);
    }

    /**
     * ProductDTOMapper bean
     * @param userDTOMapper
     * @return ProductDTOMapper
     */
    @Bean
    public ProductDTOMapper productDTOMapper(UserDTOMapper userDTOMapper) {
        return new ProductDTOMapper(userDTOMapper);
    }

    /**
     * UserDomainMapper bean
     * @return UserDomainMapper
     */
    @Bean
    public UserDomainMapper userDomainMapper() {
        return new UserDomainMapper();
    }

    /**
     * UserDTOMapper bean
     * @return UserDTOMapper
     */
    @Bean
    public UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }

    /**
     * UploadFile bean
     * @return UploadFile
     */
    @Bean
    public UploadFile uploadFile() {
        return new UploadFile();
    }

}
