package edu.study.ecommerce.application.service;

import edu.study.ecommerce.application.repository.ProductRepository;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
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
     * Saves or updates a product, handling the associated image file and setting metadata.
     * If the product already exists (ID is not null), it updates the product's details and image.
     * If the product is new, it sets the creation date, update date, and associates it with the current user.
     *
     * @param product the product to be saved or updated.
     * @param multipartFile the image file associated with the product.
     * @param httpSession the HTTP session containing the current user's ID.
     * @return the saved or updated product.
     */
    public Product saveProduct(Product product, MultipartFile multipartFile, HttpSession httpSession) {
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
        user.setId(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
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
