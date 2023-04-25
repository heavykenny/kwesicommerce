package com.example.kwesicommerce.data.model;

import com.example.kwesicommerce.utils.DateUtil;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private String status;
    private String dateCreated;
    private String dateUpdated;

    public Order(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = "pending";
        this.dateCreated = DateUtil.getCurrentDateTime();
        this.dateUpdated = this.dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void create() {
        // Handle creating a new order
    }

    public void checkStatus() {
        // Handle checking the status of an order
    }

    public void viewHistory() {
        // Handle viewing order history for the user
    }
}
