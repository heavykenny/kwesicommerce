package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
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

    public void createOrder(Order order) {
        dbHelper.insertOrder(order);
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
}
