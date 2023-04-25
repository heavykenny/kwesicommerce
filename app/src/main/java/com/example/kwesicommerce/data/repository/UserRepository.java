package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.User;
import com.example.kwesicommerce.data.sharedpreferences.SharedPreferencesHelper;
import com.example.kwesicommerce.utils.DateUtil;

public class UserRepository {

    private SQLiteDBHelper dbHelper;
    private SharedPreferencesHelper preferencesHelper;

    public UserRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
        preferencesHelper = new SharedPreferencesHelper(context);
    }

    public User getUserById(int id) {
        return dbHelper.getUser(id);
    }

    public void createUser(User user) {
        dbHelper.insertUser(user);
    }

    public void updateUser(User user) {
        dbHelper.updateUser(user);
        preferencesHelper.setUserLastUpdated(DateUtil.getCurrentDateTime());
    }

    public void deleteUser(User user) {
        dbHelper.deleteUser(user);
    }

    public int getUserId() {
        return preferencesHelper.getUserId();
    }

    public String getUserFullName() {
        return preferencesHelper.getUserFullName();
    }

    public String getUserEmail() {
        return preferencesHelper.getUserEmail();
    }

    public String getUserPassword() {
        return preferencesHelper.getUserPassword();
    }

    public String getUserHobbies() {
        return preferencesHelper.getUserHobbies();
    }

    public String getUserPostcode() {
        return preferencesHelper.getUserPostcode();
    }

    public String getUserAddress() {
        return preferencesHelper.getUserAddress();
    }

    public String getUserLastUpdated() {
        return preferencesHelper.getUserLastUpdated();
    }

    public void setUserId(int userId) {
        preferencesHelper.setUserId(userId);
    }

    public void setUserFullName(String fullName) {
        preferencesHelper.setUserFullName(fullName);
    }

    public void setUserEmail(String email) {
        preferencesHelper.setUserEmail(email);
    }

    public void setUserPassword(String password) {
        preferencesHelper.setUserPassword(password);
    }

    public void setUserHobbies(String hobbies) {
        preferencesHelper.setUserHobbies(hobbies);
    }

    public void setUserPostcode(String postcode) {
        preferencesHelper.setUserPostcode(postcode);
    }

    public void setUserAddress(String address) {
        preferencesHelper.setUserAddress(address);
    }

    public void setUserLastUpdated(String lastUpdated) {
        preferencesHelper.setUserLastUpdated(lastUpdated);
    }

    public boolean isUserAdmin() {
        int userId = preferencesHelper.getUserId();
        User user = dbHelper.getUser(userId);
        return user != null && user.isAdmin();
    }

    public void clearUserData() {
        preferencesHelper.clearUserData();
    }
}
