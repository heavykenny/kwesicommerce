package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.model.OrderModel;

import java.util.List;

public class OrderRepository {

    private SQLiteDatabaseHelper dbHelper;

    public OrderRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
    }

    public OrderModel getOrderById(int id) {
        return dbHelper.getOrder(id);
    }

    public long createOrder(OrderModel orderModel) {
        return  dbHelper.insertOrder(orderModel);
    }

    public void updateOrder(OrderModel orderModel) {
        dbHelper.updateOrder(orderModel);
    }

    public void deleteOrder(OrderModel orderModel) {
        dbHelper.deleteOrder(orderModel);
    }

    public List<OrderModel> getAllOrders() {
        return dbHelper.getOrders();
    }

    public void createOrderItem(int orderId, int productId, int quantity) {
        dbHelper.insertOrderItem(orderId, productId, quantity);
    }

    public List<CartItemModel> getOrderItems(String orderId) {
        return dbHelper.getOrderItems(orderId);
    }

    public Object getOrderTotalPrice(String orderId) {
        return dbHelper.getOrderTotalPrice(orderId);
    }

    public List<CartItemModel> getUsersOrder(int userId) {
        return dbHelper.getUsersOrder(userId);
    }

    public double getTotalSales() {
        return dbHelper.getTotalSales();
    }

    public List<CartItemModel> getAllOrdersProduct() {
        return dbHelper.getAllOrdersProduct();
    }
}
