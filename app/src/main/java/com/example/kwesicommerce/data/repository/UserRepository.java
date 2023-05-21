package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.data.sharedpreferences.SharedPreferencesHelper;

import java.util.List;

public class UserRepository {
    private final SQLiteDatabaseHelper dbHelper;
    private final SharedPreferencesHelper preferencesHelper;

    public UserRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
        preferencesHelper = new SharedPreferencesHelper(context);
    }

    public UserModel getUserById(int id) {
        return dbHelper.getUser(id);
    }

    public UserModel getUserByEmail(String email) {
        return dbHelper.getByEmail(email);
    }

    public int createUser(UserModel userModel) {
        return dbHelper.insertUser(userModel);
    }

    public boolean isEmailUnique(String email) {
        return dbHelper.isEmailUnique(email);
    }

    public void updateUser(UserModel userModel) {
        this.setUserDetails(userModel);
        dbHelper.updateUser(userModel);
    }

    public void deleteUser(UserModel userModel) {
        dbHelper.deleteUser(userModel);
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

    public String getUserHobbies() {
        return preferencesHelper.getUserHobbies();
    }

    public String getUserPostcode() {
        return preferencesHelper.getUserPostcode();
    }

    public String getUserAddress() {
        return preferencesHelper.getUserAddress();
    }

    public boolean isUserAdmin() {
        int userId = preferencesHelper.getUserId();
        UserModel userModel = dbHelper.getUser(userId);
        return userModel != null && userModel.isAdmin();
    }

    // user login method
    public boolean isUserCredentialsValid(String email, String password) {
        return dbHelper.isUserCredentialsValid(email, password);
    }

    public void setUserDetails(UserModel userModel) {
        clearUserData();
        preferencesHelper.setUserDetails(userModel);
    }

    public void clearUserData() {
        preferencesHelper.clearUserData();
    }

    public UserModel getUser() {
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

    public String getUserProfileImage() {
        return preferencesHelper.getUserProfileImage();
    }

    public List<UserModel> getAllAdmins() {
        return dbHelper.getAllAdmins();
    }

    public List<UserModel> getAllCustomers() {
        return dbHelper.getAllCustomers();
    }

    public List<UserModel> getAllUsers() {
        return dbHelper.getAllUsers();
    }
}
