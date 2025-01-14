package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.ProductService;
import edu.study.ecommerce.application.service.StockService;
import edu.study.ecommerce.domain.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;
    private final StockService stockService;

    public HomeController(ProductService productService, StockService stockService) {
        this.productService = productService;
        this.stockService = stockService;
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
     * This method retrieves the product with the specified ID from the database and adds it to the model.
     * The product detail page is then displayed.
     *
     * @param id the ID of the product to display
     * @param model the model to which the product is added
     * @return the name of the view to display
     */
    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable Integer id, Model model) {
        List<Stock> stocks = stockService.getStockByProduct(productService.getProductById(id));
        Integer lastBalance = stocks.get(stocks.size() - 1).getBalance();
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("stock", lastBalance);
        return "user/productdetail";
    }
}
