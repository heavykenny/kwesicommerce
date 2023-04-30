package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.model.Product;

import java.util.List;

public class CartRepository {
    private final SQLiteDBHelper dbHelper;

    public CartRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
    }

    public void addItemToCart(int userId, Product product) {
        dbHelper.addToCart(userId, product.getId());
    }

    public void removeItemFromCart(int userId, Product product) {
        dbHelper.removeFromCart(userId, product.getId());
    }

    public List<CartItem> getCartItems(int userId) {
        return dbHelper.getCartItems(userId);
    }

    public int getCartTotalQuantity(int userId) {
        List<CartItem> cartItems = getCartItems(userId);
        int totalQuantity = 0;
        for (CartItem item : cartItems) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public double getCartTotalPrice(int userId) {
        List<CartItem> cartItems = getCartItems(userId);
        double totalPrice = 0;
        for (CartItem item : cartItems) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public void clearCart(int userId) {
        dbHelper.clearCart(userId);
    }
}
