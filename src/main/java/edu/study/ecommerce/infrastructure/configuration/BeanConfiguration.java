package edu.study.ecommerce.infrastructure.configuration;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.application.service.ProductService;
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
}
