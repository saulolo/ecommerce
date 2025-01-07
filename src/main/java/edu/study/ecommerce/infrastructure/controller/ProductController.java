package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.ProductService;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Save product
     * @param product
     * @return String
     */
    @PostMapping("/save-product")
    public String saveProduct(Product product) {
        log.info("Nombre de producto: {}", product);
        productService.saveProduct(product);
        return "redirect:/admin";
    }

    /**
     * Show products
     * @param model
     * @return String
     */
    @GetMapping("/show")
    public String showProducts(Model model) {
        User user = new User();
        user.setId(1);
        Iterable<Product> products = productService.getProductsByUser(user);
        model.addAttribute("products", products);
        return "admin/products/show";
    }

    /**
     * Edit product
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
    Product product = productService.getProductById(id);
        log.info("Producto obtenido: {}", product);
        model.addAttribute("product", product);
        return "admin/products/edit";
    }

}
