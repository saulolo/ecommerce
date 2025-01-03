package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;

import java.time.LocalDateTime;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     * @return Iterable<Product>
     */
    public Iterable<Product> getProducts() {
        return productRepository.getProducts();
    }

    /**
     * Get products by user
     * @param user
     * @return Iterable<Product>
     */
    public Iterable<Product> getProductsByUser(User user) {
        return productRepository.getProductsByUser(user);
    }

    /**
     * Get product by id
     * @param id
     * @return Product
     */
    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    /**
     * Save product
     * @param product
     * @return Product
     */
    public Product saveProduct(Product product) {
        User user = new User();
        user.setId(1);
        product.setDateCreated(LocalDateTime.now());
        product.setDateUpdated(LocalDateTime.now());
        product.setUser(user);
        return productRepository.saveProduct(product);
    }

    /**
     * Delete product by id
     * @param id
     */
    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }

}
