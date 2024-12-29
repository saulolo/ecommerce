package edu.study.ecommerce.infrastructure.adapter;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import edu.study.ecommerce.infrastructure.mapper.ProductDomainMapper;
import edu.study.ecommerce.infrastructure.mapper.UserDomainMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductCrudRepository productCrudRepository;
    private final ProductDomainMapper productDomainMapper;
    private final UserDomainMapper userDomainMapper;

    public ProductRepositoryImpl(ProductCrudRepository productCrudRepository,
                                 ProductDomainMapper productDomainMapper,
                                 UserDomainMapper userDomainMapper) {
        this.productCrudRepository = productCrudRepository;
        this.productDomainMapper = productDomainMapper;
        this.userDomainMapper = userDomainMapper;
    }


    @Override
    public Iterable<Product> getProducts() {
        return productDomainMapper.fromProductEntitiesToProducts(productCrudRepository.findAll());
    }

    @Override
    public Iterable<Product> getProductsByUser(User user) {
        return productDomainMapper.fromProductEntitiesToProducts(productCrudRepository.findByUserEntity(userDomainMapper.fromUserToUserEntity(user)));
    }

    @Override
    public Product getProductById(Integer id) {
        return productDomainMapper.fromProductEntityToProduct(productCrudRepository.findById(id).orElse(null));
    }

    @Override
    public Product saveProduct(Product product) {
        return productDomainMapper.fromProductEntityToProduct(productCrudRepository.save(productDomainMapper.fromProductToProductEntity(product)));
    }

    @Override
    public void deleteProductById(Integer id) {
        productCrudRepository.deleteById(id);
    }
}
