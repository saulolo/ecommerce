package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.StockService;
import edu.study.ecommerce.application.service.ValidateStock;
import edu.study.ecommerce.domain.Product;
import edu.study.ecommerce.domain.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping ("/admin/products/stock")
public class StockController {

    private final StockService stockService;
    private final ValidateStock validateStock;

    public StockController(StockService stockService, ValidateStock validateStock) {
        this.stockService = stockService;
        this.validateStock = validateStock;
    }


    /**
     * Handles GET requests to display the stock information for a specific product.
     *
     * @param id the ID of the product whose stock information is to be displayed
     * @param model the model to which attributes are added for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Product product = new Product();
        product.setId(id);
        List<Stock> stocks = stockService.getStockByProduct(product);
        model.addAttribute("stocks", stocks);
        model.addAttribute("idProduct", id);
        return "admin/stock/show";
    }

    /**
     * Handles GET requests to display the form for adding units to a specific product's stock.
     *
     * @param id the ID of the product to which units are to be added
     * @param model the model to which attributes are added for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("create-unit-product/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("idProduct", id);
        return "admin/stock/create";
    }

    /**
     * Handles POST requests to save the stock information for a specific product.
     *
     * @param stock the stock information to be saved
     * @param idProduct the ID of the product whose stock information is to be saved
     * @return the URL to which the client is redirected
     */
    @PostMapping("/save-unit-product")
    public String save(Stock stock, @RequestParam("idProduct") Integer idProduct) {
        stock.setDateCreated(LocalDateTime.now());
        stock.setDescription("inventario");
        Product product = new Product();
        product.setId(idProduct);
        stock.setProduct(product);
        stockService.saveStock(validateStock.calculateBalance(stock));
        return "redirect:/admin/products/show";
    }

}
