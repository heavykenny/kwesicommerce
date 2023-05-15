package com.example.kwesicommerce.data.model;

import com.example.kwesicommerce.utils.FunctionUtil;

public class Product {
    private String name;
    private String description;

    private int quantity;


    private String imageUrl;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private double price;
    private double listPrice;
    private double retailPrice;
    private String dateCreated;
    private String dateUpdated;
    private int id;
    private int categoryId;

    public Product(int productId, String name, String description, String imageUrl, int productQuantity, double price, double listPrice, double retailPrice, int categoryId) {
        this.id = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = productQuantity;
        this.price = price;
        this.listPrice = listPrice;
        this.retailPrice = retailPrice;
        this.dateCreated = FunctionUtil.getCurrentDateTime();
        this.dateUpdated = this.dateCreated;
        this.categoryId = categoryId;
    }

    public Product() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.imageUrl = "";
        this.price = 0;
        this.listPrice = 0;
        this.retailPrice = 0;
        this.dateCreated = FunctionUtil.getCurrentDateTime();
        this.dateUpdated = this.dateCreated;
        this.categoryId = 0;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
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
        // Handle creating a new product
    }

    public void update() {
        // Handle updating a product
    }

    public void delete() {
        // Handle deleting a product
    }
}
