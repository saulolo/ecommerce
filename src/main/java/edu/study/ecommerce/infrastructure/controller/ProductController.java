package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.ProductService;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping ("/admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create product
     * @return String
     */
    @GetMapping("/create")
    public String create() {
        return "admin/products/create";
    }

    /**
     * Handles POST requests to save or update a product.
     * This method logs the product details, saves the product with its associated image, and redirects to the admin page.
     *
     * @param product the product to be saved or updated.
     * @param multipartFile the image file associated with the product.
     * @param httpSession the HTTP session containing the current user's ID.
     * @return a redirect to the admin page ("/admin").
     */
    @PostMapping("/save-product")
    public String saveProduct(Product product, @RequestParam("img") MultipartFile multipartFile, HttpSession httpSession) {
        log.info("Nombre de producto: {}", product);
        productService.saveProduct(product, multipartFile, httpSession);
        return "redirect:/admin";
    }

    /**
     * Handles GET requests to display products associated with the current user.
     * This method retrieves the user from the session, fetches their products, and adds them to the model for rendering.
     *
     * @param model the model to which the products are added for rendering the view.
     * @param httpSession the HTTP session containing the current user's ID.
     * @return the name of the view to be rendered ("admin/products/show").
     */
    @GetMapping("/show")
    public String showProducts(Model model, HttpSession httpSession) {
        log.info("id user desde la variable de session: {}", httpSession.getAttribute("iduser").toString());
        User user = new User();
        user.setId(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        Iterable<Product> products = productService.getProductsByUser(user);
        model.addAttribute("products", products);
        return "admin/products/show";
    }

    /**
     * Handles GET requests to display the edit form for a specific product.
     * This method retrieves the product by its ID, logs the product details, and adds it to the model for rendering the edit form.
     *
     * @param id the ID of the product to be edited.
     * @param model the model to which the product is added for rendering the view.
     * @return the name of the view to be rendered ("admin/products/edit").
     */
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
    Product product = productService.getProductById(id);
        log.info("Producto obtenido: {}", product);
        model.addAttribute("product", product);
        return "admin/products/edit";
    }

    /**
     * Handles GET requests to delete a specific product by its ID.
     * This method deletes the product and redirects to the products listing page.
     *
     * @param id the ID of the product to be deleted.
     * @return a redirect to the products listing page ("/admin/products/show").
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products/show";
    }
}
