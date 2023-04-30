package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.User;
import com.example.kwesicommerce.data.sharedpreferences.SharedPreferencesHelper;

public class UserRepository {

    private final SQLiteDBHelper dbHelper;
    private final SharedPreferencesHelper preferencesHelper;

    public UserRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
        preferencesHelper = new SharedPreferencesHelper(context);
    }

    public User getUserById(int id) {
        return dbHelper.getUser(id);
    }

    public User getUserByEmail(String email) {
        return dbHelper.getByEmail(email);
    }

    public void createUser(User user) {
        dbHelper.insertUser(user);
    }

    public boolean isEmailUnique(String email) {
        return dbHelper.isEmailUnique(email);
    }

    public void updateUser(User user) {
        dbHelper.updateUser(user);
    }

    public void deleteUser(User user) {
        dbHelper.deleteUser(user);
    }

    public int getUserId() {
        return preferencesHelper.getUserId();
    }

    public void setUserId(int userId) {
        preferencesHelper.setUserId(userId);
    }

    public String getUserFullName() {
        return preferencesHelper.getUserFullName();
    }

    public void setUserFullName(String fullName) {
        preferencesHelper.setUserFullName(fullName);
    }

    public String getUserEmail() {
        return preferencesHelper.getUserEmail();
    }

    public void setUserEmail(String email) {
        preferencesHelper.setUserEmail(email);
    }

    public String getUserPassword() {
        return preferencesHelper.getUserPassword();
    }

    public void setUserPassword(String password) {
        preferencesHelper.setUserPassword(password);
    }

    public String getUserHobbies() {
        return preferencesHelper.getUserHobbies();
    }

    public void setUserHobbies(String hobbies) {
        preferencesHelper.setUserHobbies(hobbies);
    }

    public String getUserPostcode() {
        return preferencesHelper.getUserPostcode();
    }

    public void setUserPostcode(String postcode) {
        preferencesHelper.setUserPostcode(postcode);
    }

    public String getUserAddress() {
        return preferencesHelper.getUserAddress();
    }

    public void setUserAddress(String address) {
        preferencesHelper.setUserAddress(address);
    }

    public String getUserLastUpdated() {
        return preferencesHelper.getUserLastUpdated();
    }

    public void setDateUpdated(String dateUpdated) {
        preferencesHelper.setDateUpdated(dateUpdated);
    }

    public boolean isUserAdmin() {
        int userId = preferencesHelper.getUserId();
        User user = dbHelper.getUser(userId);
        return user != null && user.isAdmin();
    }

    // user login method
    public boolean isUserCredentialsValid(String email, String password) {
        return dbHelper.isUserCredentialsValid(email, password);
    }

    public void setUserDetails(User user) {
        clearUserData();
        preferencesHelper.setUserDetails(user);
    }

    public void clearUserData() {
        preferencesHelper.clearUserData();
    }

    public User getUser() {
        int userId = preferencesHelper.getUserId();
        return dbHelper.getUser(userId);
    }

    public boolean isUserLoggedIn() {
        return preferencesHelper.getUserId() > 0;
    }

    public boolean getUserAdmin() {
        return preferencesHelper.getUserAdmin();
    }

    public void setUserAdmin(boolean value) {
        if (value) {
            if (this.isUserAdmin()) {
                preferencesHelper.setUserAdmin(true);
            } else {
                throw new RuntimeException("You are not an admin");
            }
        } else {
            preferencesHelper.setUserAdmin(false);
        }
    }
}
