package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.*;
import edu.study.ecommerce.domain.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * Handles GET requests to show the summary of the order.
     * This method retrieves the items in the cart, the total price, and the user from the session.
     *
     * <p><strong>Flow:</strong>
     * <ol>
     *   <li>Retrieves the items in the cart.</li>
     *   <li>Retrieves the total price of the cart.</li>
     *   <li>Retrieves the user from the session.</li>
     *   <li>Adds the items, total price, and user to the model.</li>
     *   <li>Displays the summary order page.</li>
     * </ol>
     *
     * @param model the model to which the items, total price, and user are added.
     * @param httpSession the HTTP session containing the current user's ID.
     * @return the name of the view to show the summary of the order.
     */
    @GetMapping("/summary-order")
    public String showSummaryOrder(Model model, HttpSession httpSession) {
        log.info("id user desde la variable de session: {}", httpSession.getAttribute("iduser").toString());
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        model.addAttribute("user", user);
        model.addAttribute("id", httpSession.getAttribute("iduser").toString());
        return "user/sumaryorder";
    }

    /**
     * Handles GET requests to create an order.
     * This method creates a new order and associates it with the user.
     * It also associates the products in the cart with the order and updates the stock.
     *
     * <p><strong>Flow:</strong>
     * <ol>
     *   <li>Retrieves the user from the session.</li>
     *   <li>Creates a new order and associates it with the user.</li>
     *   <li>Iterates through the cart items and associates them with the order.</li>
     *   <li>Saves each product in the order and updates the stock.</li>
     *   <li>Clears the shopping cart.</li>
     *   <li>Redirects the user to the home page.</li>
     * </ol>
     *
     * @param attributes the attributes to add to the redirect.
     * @param httpSession the HTTP session containing the current user's ID.
     * @return the name of the view to show the summary of the order.
     */
    @GetMapping("/create-order")
    public String createOrder(RedirectAttributes attributes, HttpSession httpSession) {
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
        attributes.addFlashAttribute("id", httpSession.getAttribute("iduser").toString());

        // Redirect the user to the home page
        return "redirect:/home";
    }
}
