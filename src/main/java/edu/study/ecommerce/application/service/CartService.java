package edu.study.ecommerce.application.service;

import edu.study.ecommerce.domain.ItemCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartService {

    private final List<ItemCart> itemCarts;
    private final HashMap<Integer, ItemCart> itemCartHashMap;

    public CartService() {
        this.itemCartHashMap = new HashMap<>();
        this.itemCarts = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart.
     * @param quantity the quantity of the product.
     * @param idProduct the ID of the product.
     * @param nameProduct the name of the product.
     * @param price the price of the product.
     */
    public void addItemCart(Integer quantity, Integer idProduct, String nameProduct, BigDecimal price) {
        ItemCart itemCart = new ItemCart(idProduct, nameProduct, quantity, price);
        itemCartHashMap.put(itemCart.getIdProduct(), itemCart);
        fillList();
    }

    /**
     * Calculates the total price of all items in the cart.
     * @return the total price as a BigDecimal.
     */
    public BigDecimal getTotalCart() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCart itemCart : itemCarts) {
            total = total.add(itemCart.getTotalPriceItem());
        }
        return total;
    }

    /**
     * Updates the itemCarts list with the current items in the itemCartHashMap.
     */
    private void fillList() {
        itemCarts.clear();
        itemCartHashMap.forEach(
                (integer, itemCart) -> itemCarts.add(itemCart)
        );
    }

    /**
     * Removes an item from the cart by its product ID.
     * @param idProduct the ID of the product to remove.
     */
    public void removeItemCart(Integer idProduct) {
        itemCartHashMap.remove(idProduct);
        fillList();
    }

    /**
     * Removes all items from the cart.
     */
    public void removeAllItemsCart() {
        itemCartHashMap.clear();
        itemCarts.clear();
    }

    //Solo es para mirar por consola
    public List<ItemCart> getItemCarts() {
        return itemCarts;
    }
}
