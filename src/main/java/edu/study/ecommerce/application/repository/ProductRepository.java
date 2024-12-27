package edu.study.ecommerce.application.repository;

import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    /**
     * Get all products
     * @return Iterable<Product>
     */
    Iterable<Product> getProducts();

    /**
     * Get products by user
     * @param user
     * @return Iterable<Product>
     */
    Iterable<Product> getProductsByUser(User user);

    /**
     * Get product by id
     * @param id
     * @return Product
     */
    Product getProductById(Integer id);

    /**
     * Save product
     * @param product
     * @return Product
     */
    Product saveProduct(Product product);

    /**
     * Delete product by id
     * @param id
     */
    void deleteProductById(Integer id);
}
