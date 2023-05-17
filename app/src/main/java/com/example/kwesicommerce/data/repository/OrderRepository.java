package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.model.Order;

import java.util.List;

public class OrderRepository {

    private SQLiteDBHelper dbHelper;

    public OrderRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
    }

    public Order getOrderById(int id) {
        return dbHelper.getOrder(id);
    }

    public long createOrder(Order order) {
        return  dbHelper.insertOrder(order);
    }

    public void updateOrder(Order order) {
        dbHelper.updateOrder(order);
    }

    public void deleteOrder(Order order) {
        dbHelper.deleteOrder(order);
    }

    public List<Order> getAllOrders() {
        return dbHelper.getOrders();
    }

    public void createOrderItem(int orderId, int productId, int quantity) {
        dbHelper.insertOrderItem(orderId, productId, quantity);
    }

    public List<CartItem> getOrderItems(String orderId) {
        return dbHelper.getOrderItems(orderId);
    }

    public Object getOrderTotalPrice(String orderId) {
        return dbHelper.getOrderTotalPrice(orderId);
    }

    public List<CartItem> getUsersOrder(int userId) {
        return dbHelper.getUsersOrder(userId);
    }

    public double getTotalSales() {
        return dbHelper.getTotalSales();
    }

    public List<CartItem> getAllOrdersProduct() {
        return dbHelper.getAllOrdersProduct();
    }
}
