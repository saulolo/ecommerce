package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Displays the home page with the list of products.
     *
     * This method retrieves the list of products from the database and adds it to the model.
     * The list of products is then displayed on the home page.
     *
     * @param model the model to which the list of products is added
     * @return the name of the view to display
     */
    @GetMapping
    public String home(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    /**
     * Displays the product detail page.
     *
     * This method displays the product detail page for the specified product.
     *
     * @return the name of the view to display
     */
    @GetMapping("/product-detail/{id}")
    public String productDetail() {
        return "user/productdetail";
    }
}
