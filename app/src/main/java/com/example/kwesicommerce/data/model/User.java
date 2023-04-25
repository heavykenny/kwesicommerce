package com.example.kwesicommerce.data.model;

public class User {
    private final String fullName;
    private final String email;
    private final String dateRegistered;
    private final String dateUpdated;
    private final String password;
    private final String hobbies;
    private final String postcode;

    public boolean isAdmin() {
        return isAdmin;
    }

    private final String address;
    private int id;
    private final boolean isAdmin;

    public User(String fullName, String email, String dateRegistered, String dateUpdated, String password, String hobbies, String postcode, String address, Boolean isAdmin) {
        this.fullName = fullName;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.dateUpdated = dateUpdated;
        this.password = password;
        this.hobbies = hobbies;
        this.postcode = postcode;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getPassword() {
        return password;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }

    public void register() {
        // Handle user registration
    }

    public void login() {
        // Handle user login
    }

    public void updateProfile() {
        // Handle updating user profile
    }

    public void checkOrderStatus() {
        // Handle checking order status for the user
    }

    public void viewPreviousOrders() {
        // Handle viewing previous orders for the user
    }
}
