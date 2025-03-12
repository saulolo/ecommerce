package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.*;
import edu.study.ecommerce.domain.*;
import jakarta.servlet.http.HttpSession;
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
    private final Integer UNIT_IN = 0;

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
    public String showSummaryOrder(Model model, HttpSession httpSession) {
        log.info("id user desde la variable de session: {}", httpSession.getAttribute("iduser").toString());
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        return "user/sumaryorder";
    }

    /**
     * Handles GET requests to create a new order.
     * This method retrieves the current user from the session, creates a new order, associates it with the user,
     * and processes the items in the cart to create order products and update the stock.
     *
     * <p><strong>Flow:</strong>
     * <ol>
     *   <li>Retrieves the current user from the session.</li>
     *   <li>Creates a new order and associates it with the user.</li>
     *   <li>Iterates through the cart items and creates order products.</li>
     *   <li>Updates the stock for each product in the order.</li>
     *   <li>Redirects to the home page after completing the order.</li>
     * </ol>
     *
     * @param httpSession the HTTP session containing the current user's ID.
     * @return a redirect to the home page ("/home").
     */
    @GetMapping("/create-order")
    public String createOrder(HttpSession httpSession) {
        log.info("Crear Orden...");
        log.info("id user desde la variable de session: {}", httpSession.getAttribute("iduser").toString());
        // Retrieve a user from the database with ID 1
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));

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
                stock.setDescription("Venta");
                stock.setUnitIn(UNIT_IN);
                stock.setUnitOut(op.getQuantity());
                stockService.saveStock(validateStock.calculateBalance(stock));
            });
        }

        // Clear the shopping cart
        cartService.removeAllItemsCart();

        // Redirect the user to the home page
        return "redirect:/home";
    }

}
