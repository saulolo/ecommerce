package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class ProductService {

    private final ProductRepository productRepository;
    private final UploadFile uploadFile;

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
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
     * Saves a product in the repository, allowing for an optional image file to be uploaded.
     * If the product already exists (has an ID), it updates the product's fields while maintaining
     * its code, user, and creation date. If a new image is provided, it uploads the image and deletes
     * the old one (if applicable). If no image is provided, it retains the previously stored image.
     *
     * @param product      object to be saved or updated.
     *                     If the product's ID is null, it is treated as a new product.
     * @param multipartFile representing the image to be uploaded.
     *                      If no image is provided, the existing image (if any) is retained.
     * @return the saved {@link Product} object, including updates or changes applied during the process.
     * @throws IllegalArgumentException if the provided multipart file is invalid or an error occurs during file upload.
     */
    public Product saveProduct(Product product, MultipartFile multipartFile) {
        if (product.getId() != null) {
            Product productDB = productRepository.getProductById(product.getId());
            if (multipartFile.isEmpty()) {
                product.setImage(productDB.getImage());
            } else {
                if (!productDB.getImage().equals("default.jpg")) {
                    uploadFile.delete(productDB.getImage());
                }
                product.setImage(uploadFile.upload(multipartFile));

            }
            product.setCode(productDB.getCode());
            product.setUser(productDB.getUser());
            product.setDateCreated(productDB.getDateCreated());
            product.setDateUpdated(LocalDateTime.now());
        }
        User user = new User();
        user.setId(1);
        product.setDateCreated(LocalDateTime.now());
        product.setDateUpdated(LocalDateTime.now());
        product.setUser(user);
        product.setImage(uploadFile.upload(multipartFile));
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
