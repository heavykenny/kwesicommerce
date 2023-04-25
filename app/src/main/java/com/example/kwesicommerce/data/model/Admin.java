package com.example.kwesicommerce.data.model;

import java.util.List;

public class Admin {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createCategory(Category category) {
        // Handle creating a new category
    }

    public void updateCategory(Category category) {
        // Handle updating a category
    }

    public void deleteCategory(Category category) {
        // Handle deleting a category
    }

    public void createProduct(Product product) {
        // Handle creating a new product
    }

    public void updateProduct(Product product) {
        // Handle updating a product
    }

    public void deleteProduct(Product product) {
        // Handle deleting a product
    }

    public List<Order> viewOrders() {
        // Return a list of all orders
        return null;
    }

    public List<User> viewUsers() {
        // Return a list of all users
        return null;
    }

    public void updateUser(User user) {
        // Handle updating a user
    }

    public void deleteUser(User user) {
        // Handle deleting a user
    }

    public void exportOrdersToFile() {
        // Handle exporting all orders to a file
    }
}

