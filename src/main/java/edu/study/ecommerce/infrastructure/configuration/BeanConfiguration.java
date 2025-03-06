package edu.study.ecommerce.infrastructure.configuration;

import edu.study.ecommerce.application.repository.*;
import edu.study.ecommerce.application.service.*;
import edu.study.ecommerce.infrastructure.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class BeanConfiguration {


    /**
     * ProductService bean
     * @param productRepository
     * @param uploadFile
     * @return ProductService
     */
    @Bean
    public ProductService productService(ProductRepository productRepository, UploadFile uploadFile) {
        return new ProductService(productRepository, uploadFile);
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

    /**
     * StockService bean
     * @param stockRepository
     * @param stockMapper
     * @return StockService
     */
    @Bean
    public StockService stockService(StockRepository stockRepository, StockMapper stockMapper) {
        return new StockService(stockRepository, stockMapper);
    }

    /**
     * ProductMapper bean
     * @return ProductMapper
     */
    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    /**
     * StockMapper bean
     * @param productMapper
     * @return StockMapper
     */
    @Bean
    public StockMapper stockMapper(ProductMapper productMapper) {
        return new StockMapper(productMapper);
    }

    /**
     * ValidateStock bean
     * @param stockService
     * @return ValidateStock
     */
    @Bean
    public ValidateStock validateStock(StockService stockService) {
        return new ValidateStock(stockService);
    }

    /**
     * OrderService bean
     * @param orderRepository
     * @return OrderService
     */
    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);
    }

    /**
     * Creates a bean for OrderProductService.
     * @param orderProductRepository the repository to be injected.
     * @return the OrderProductService instance.
     */
    @Bean
    public OrderProductService orderProductService(OrderProductRepository orderProductRepository) {
        return new OrderProductService(orderProductRepository);
    }

    /**
     * Creates a bean for OrderMapper.
     * @param userDomainMapper the UserDomainMapper to be injected.
     * @return the OrderMapper instance.
     */
    @Bean
    public OrderMapper orderMapper(UserDomainMapper userDomainMapper) {
        return new OrderMapper(userDomainMapper);
    }

    /**
     * Creates a bean for OrderProductMapper.
     * @param productMapper the ProductMapper to be injected.
     * @param orderMapper the OrderMapper to be injected.
     * @return the OrderProductMapper instance.
     */
    @Bean
    public OrderProductMapper orderProductMapper(ProductMapper productMapper, OrderMapper orderMapper) {
        return new OrderProductMapper(productMapper, orderMapper);
    }

    /**
     * Creates a session-scoped bean for CartService.
     * The bean is proxied to support session scope in a Spring context.
     * @return the CartService instance.
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CartService cartService() {
        return new CartService();
    }

    /**
     * Creates and configures a bean for the UserService.
     * This method initializes the UserService with the provided UserRepository.
     *
     * @param userRepository the repository to be injected into the UserService.
     * @return a new instance of UserService configured with the given UserRepository.
     */
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    /**
     * Creates and configures a bean for the RegistrationService.
     * This method initializes the RegistrationService with the provided UserService.
     *
     * @param userService the service to be injected into the RegistrationService.
     * @return a new instance of RegistrationService configured with the given UserService.
     */
    @Bean
    public RegistrationService registrationService(UserService userService) {
        return new RegistrationService(userService);
    }

    /**
     * Creates and configures a bean for the LoginService.
     * This method initializes the LoginService with the provided UserService.
     *
     * @param userService the service to be injected into the LoginService.
     * @return a new instance of LoginService configured with the given UserService.
     */
    @Bean
    public LoginService loginService(UserService userService) {
        return new LoginService(userService);
    }
}
