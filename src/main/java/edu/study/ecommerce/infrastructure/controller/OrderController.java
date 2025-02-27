package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.*;
import edu.study.ecommerce.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/orders")
public class OrderController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
    private final StockService stockService;
    private final ValidateStock validateStock;

    public OrderController(CartService cartService, UserService userService, ProductService productService, OrderService orderService, OrderProductService orderProductService, StockService stockService, ValidateStock validateStock) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
        this.stockService = stockService;
        this.validateStock = validateStock;
    }


    /**
     * Handles GET requests to display the summary order page.
     * Retrieves the current user, cart items, and total price, and adds them to the model for rendering.
     *
     * @param model the model to which attributes are added for rendering the view.
     * @return the name of the view to be rendered ("user/sumaryorder").
     */
    @GetMapping("/summary-order")
    public String showSummaryOrder(Model model) {
        User user = userService.findById(1);
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        return "user/sumaryorder";
    }

    /**
     * Handles the GET request to create a new order.
     * <p>
     * This method performs the following actions:
     * <ul>
     *     <li>Retrieves a user with ID 1.</li>
     *     <li>Creates a new order and saves it in the database.</li>
     *     <li>Iterates through the shopping cart items and associates them with the order.</li>
     *     <li>Saves the order products in the database.</li>
     *     <li>Registers a stock entry with the description "For sale".</li>
     *     <li>Removes all items from the cart after processing the order.</li>
     * </ul>
     * </p>
     *
     * @return A redirection to the home page.
     */
    @GetMapping("/create-order")
    public String createOrder() {
        log.info("Create order...");

        // Retrieve a user from the database with ID 1
        User user = userService.findById(1);

        // Create a new order and associate it with the user
        Order order = new Order();
        order.setDateCreated(LocalDateTime.now());
        order.setUser(user);
        order = orderService.saveOrder(order);

        // List to store the order products
        List<OrderProduct> orderProducts = new ArrayList<>();

        // Iterate through the cart items and associate them with the order
        for (ItemCart itemCart : cartService.getItemCarts()) {
            orderProducts.add(new OrderProduct(
                    productService.getProductById(itemCart.getIdProduct()),
                    itemCart.getQuantity(),
                    order));

            // Save each product in the order and update the stock
            orderProducts.forEach(op -> {
                orderProductService.create(op);

                Stock stock = new Stock();
                stock.setDateCreated(LocalDateTime.now());
                stock.setProduct(op.getProduct());
                stock.setDescription("For sale");
                stock.setUnitIn(op.getQuantity());
                stockService.saveStock(validateStock.calculateBalance(stock));
            });

            // Clear the shopping cart
            cartService.removeAllItemsCart();
        }

        // Redirect the user to the home page
        return "redirect:/home";
    }

}
