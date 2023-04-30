package com.example.kwesicommerce.data.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product) {
        // Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity();
                return;
            }
        }

        // If not, add a new cart item
        CartItem newItem = new CartItem(product, 1);
        items.add(newItem);
    }

    public void removeItem(Product product) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == product.getId()) {
                iterator.remove();
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (CartItem item : items) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public void clearCart() {
        items.clear();
    }
}

