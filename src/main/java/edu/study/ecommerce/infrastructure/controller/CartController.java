package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Slf4j
@Controller
@RequestMapping("/user/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Handles the addition of a product to the shopping cart.
     * Redirects to the home page after adding the product.
     *
     * @param quantity the quantity of the product to add.
     * @param idProduct the ID of the product to add.
     * @param nameProduct the name of the product to add.
     * @param price the price of the product to add.
     * @return a redirect to the home page.
     */
    @PostMapping("/add-product")
    public String addProductCart(@RequestParam Integer quantity,
                                 @RequestParam Integer idProduct,
                                 @RequestParam String nameProduct,
                                 @RequestParam BigDecimal price) {
        cartService.addItemCart(quantity, idProduct, nameProduct, price);
        cartService.getItemCarts().forEach(
                itemCart -> log.info("Item cart: {}", itemCart)
        );
        return "redirect:/home";
    }

}
