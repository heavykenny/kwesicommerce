package com.example.kwesicommerce.data.model;

import com.example.kwesicommerce.utils.DateUtil;

public class Product {
    private final String name;
    private final String description;
    private final String imageUrl;
    private final double price;
    private final double listPrice;
    private final double retailPrice;
    private final String dateCreated;
    private final String dateUpdated;
    private int id;
    private Category category;
    public Product(String name, String description, String imageUrl, double price, double listPrice, double retailPrice, Category category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.listPrice = listPrice;
        this.retailPrice = retailPrice;
        this.dateCreated = DateUtil.getCurrentDateTime();
        this.dateUpdated = this.dateCreated;
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
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

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getListPrice() {
        return listPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
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

    public void assignCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
