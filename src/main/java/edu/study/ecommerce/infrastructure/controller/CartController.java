package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * Handles POST requests to add a product to the shopping cart.
     * Redirects to the home page after adding the product.
     *
     * @param quantity the quantity of the product to add
     * @param idProduct the ID of the product to add
     * @param nameProduct the name of the product to add
     * @param price the price of the product to add
     * @return a redirect to the home page
     */
    @PostMapping("/add-product")
    public String addProductCart(@RequestParam Integer quantity,
                                 @RequestParam Integer idProduct,
                                 @RequestParam String nameProduct,
                                 @RequestParam BigDecimal price) {
        cartService.addItemCart(quantity, idProduct, nameProduct, price);
        showCart();
        return "redirect:/home";
    }

    /**
     * Logs the items in the shopping cart for debugging or informational purposes.
     * Iterates through the cart items and logs each item using the application's logger.
     */
    private void showCart() {
        cartService.getItemCarts().forEach(
                itemCart -> log.info("Item cart: {}", itemCart)
        );
    }

    /**
     * Handles GET requests to display the shopping cart.
     * Adds the cart items and total price to the model for rendering the view.
     *
     * @param model the model to which attributes are added for rendering the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/get-cart")
    public String getCart(Model model) {
        showCart();
        model.addAttribute("cart", cartService.getItemCarts());
        model.addAttribute("total", cartService.getTotalCart());
        return "user/cart/cart";
    }

    /**
     * Handles GET requests to delete an item from the shopping cart by its ID.
     * Redirects to the cart page after removing the item.
     *
     * @param id the ID of the item to remove from the cart
     * @return a redirect to the cart page
     */
    @GetMapping("/delete-item-cart/{id}")
    public String deleteItemCart(@PathVariable Integer id) {
        cartService.removeItemCart(id);
        return "redirect:/user/cart/get-cart";
    }

}
