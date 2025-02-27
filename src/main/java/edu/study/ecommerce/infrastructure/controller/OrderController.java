package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.CartService;
import edu.study.ecommerce.application.service.UserService;
import edu.study.ecommerce.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/user/orders")
public class OrderController {

    private final CartService cartService;
    private final UserService userService;

    public OrderController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
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
}
