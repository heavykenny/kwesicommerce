package com.example.kwesicommerce.data.model;

public class OrderModel {
    private int id;
    private int userId;
    private String status;

    private String orderTrackingNumber;
    private String dateCreated;
    private String dateUpdated;

    private double amountPaid;

    public OrderModel(int userId, String status, String orderTrackingNumber, double amountPaid, String dateCreated, String dateUpdated) {
        this.userId = userId;
        this.status = status;
        this.orderTrackingNumber = orderTrackingNumber;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.amountPaid = amountPaid;
    }

    public OrderModel() {
        this.id = 0;
        this.userId = 0;
        this.status = "";
        this.orderTrackingNumber = "";
        this.dateCreated = "";
        this.dateUpdated = "";
        this.amountPaid = 0;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
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


}
