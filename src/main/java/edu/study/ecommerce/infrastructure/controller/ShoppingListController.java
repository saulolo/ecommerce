package edu.study.ecommerce.infrastructure.controller;

import edu.study.ecommerce.application.service.OrderProductService;
import edu.study.ecommerce.application.service.OrderService;
import edu.study.ecommerce.application.service.UserService;
import edu.study.ecommerce.domain.Order;
import edu.study.ecommerce.domain.OrderProduct;
import edu.study.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart/shopping")
public class ShoppingListController {

    private final OrderService orderService;
    private final UserService userService;
    private final OrderProductService orderProductService;

    public ShoppingListController(OrderService orderService, UserService userService, OrderProductService orderProductService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderProductService = orderProductService;
    }

    /**
     * Displays the shopping list for the current user.
     * Retrieves the user's orders, processes them to include associated products, and renders the view.
     *
     * @param model the model to which the orders are added.
     * @param httpSession the HTTP session containing the user's ID.
     * @return the view name ("user/shoppingList").
     */
    public String showShoppingList(Model model, HttpSession httpSession) {
        List<Order> newListOrder = new ArrayList<>();
        User user = userService.findById(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
        Iterable<Order> orders = orderService.getOrdersByUsers(user);
        for (Order order : orders) {
            newListOrder.add(getOrdersProducts(order));
        }
        model.addAttribute("orders", newListOrder);
        return "user/shoppingList";
    }

    /**
     * Retrieves and associates the products for a given order.
     * This method fetches the order products associated with the provided order and adds them to the order object.
     *
     * @param order the order for which to retrieve and associate products.
     * @return the updated order with the associated products.
     */
    private Order getOrdersProducts(Order order) {
        Iterable<OrderProduct> orderProducts = orderProductService.getOrderProductsByOrder(order);
        order.addOrdersProduct((List<OrderProduct>) orderProducts);
        return order;
    }
}
