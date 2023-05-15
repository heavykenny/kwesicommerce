package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.model.Order;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.utils.FunctionUtil;

import java.util.List;

public class CartRepository {
    private final SQLiteDBHelper dbHelper;
    private final Context context;

    public CartRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
        this.context = context;
    }

    public void addItemToCart(int userId, int productId, int quantity) {
        dbHelper.addToCart(userId, productId, quantity);
    }

    public void removeItemFromCart(int cartId) {
        dbHelper.removeFromCart(cartId);
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

    public void updateCartItemQuantity(int userId, int quantity) {
        dbHelper.updateCartItemQuantity(userId, quantity);
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

    public long makeOrder(int userId) {
        List<CartItem> cartItems = getCartItems(userId);
        OrderRepository orderRepository = new OrderRepository(context);
        Order order = new Order(userId, "Paid",FunctionUtil.generateOrderTracking(), this.getCartTotalPrice(userId), FunctionUtil.getCurrentDateTime(), FunctionUtil.getCurrentDateTime());
        long orderId = orderRepository.createOrder(order);

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
            dbHelper.updateProduct(product);
            orderRepository.createOrderItem((int) orderId, item.getProduct().getId(), item.getQuantity());
            dbHelper.removeFromCart(item.getId());
        }

        return orderId;
    }
}
