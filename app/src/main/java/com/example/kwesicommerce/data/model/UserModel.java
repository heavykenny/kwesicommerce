package com.example.kwesicommerce.data.model;

import java.util.Date;

public class UserModel {
    private String fullName;
    private String email;
    private String dateRegistered;
    private String dateUpdated;
    private String password;
    private String hobbies;
    private String postcode;
    private String address;

    public String getProfileImage() {
        return setProfileImage;
    }

    public void setProfileImage(String setProfileImage) {
        this.setProfileImage = setProfileImage;
    }

    private String setProfileImage;
    private boolean isAdmin;
    private int id;

    public UserModel(String firstName, String lastName, String email, String password) {
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.dateRegistered = new Date().toString();
        this.dateUpdated = new Date().toString();
        this.password = password;
        this.hobbies = "";
        this.postcode = "";
        this.address = "";
        this.isAdmin = false;
        this.setProfileImage = "";
    }

    public UserModel(String firstName, String lastName, String email, String password, String hobbies, String postcode, String address, Boolean isAdmin, String setProfileImage) {
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.dateRegistered = new Date().toString();
        this.dateUpdated = new Date().toString();
        this.password = password;
        this.hobbies = hobbies;
        this.postcode = postcode;
        this.address = address;
        this.isAdmin = isAdmin;
        this.setProfileImage = setProfileImage;
    }

    public UserModel() {
        this.fullName = "";
        this.email = "";
        this.dateRegistered = "";
        this.dateUpdated = "";
        this.password = "";
        this.hobbies = "";
        this.postcode = "";
        this.address = "";
        this.isAdmin = false;
        this.setProfileImage = "";
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
