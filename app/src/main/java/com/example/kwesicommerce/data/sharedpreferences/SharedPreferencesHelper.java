package com.example.kwesicommerce.data.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kwesicommerce.data.model.User;

public class SharedPreferencesHelper {

    private static final String PREF_NAME = "myapp_pref";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_FULLNAME = "user_fullname";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_PASSWORD = "user_password";
    private static final String KEY_USER_HOBBIES = "user_hobbies";
    private static final String KEY_USER_POSTCODE = "user_postcode";
    private static final String KEY_USER_ADDRESS = "user_address";
    private static final String KEY_USER_LAST_UPDATED = "user_last_updated";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }

    public void setUserId(int userId) {
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public String getUserFullName() {
        return sharedPreferences.getString(KEY_USER_FULLNAME, "");
    }

    public void setUserFullName(String fullName) {
        sharedPreferences.edit().putString(KEY_USER_FULLNAME, fullName).apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public void setUserEmail(String email) {
        sharedPreferences.edit().putString(KEY_USER_EMAIL, email).apply();
    }

    public String getUserPassword() {
        return sharedPreferences.getString(KEY_USER_PASSWORD, "");
    }

    public void setUserPassword(String password) {
        sharedPreferences.edit().putString(KEY_USER_PASSWORD, password).apply();
    }

    public String getUserHobbies() {
        return sharedPreferences.getString(KEY_USER_HOBBIES, "");
    }

    public void setUserHobbies(String hobbies) {
        sharedPreferences.edit().putString(KEY_USER_HOBBIES, hobbies).apply();
    }

    public String getUserPostcode() {
        return sharedPreferences.getString(KEY_USER_POSTCODE, "");
    }

    public void setUserPostcode(String postcode) {
        sharedPreferences.edit().putString(KEY_USER_POSTCODE, postcode).apply();
    }

    public String getUserAddress() {
        return sharedPreferences.getString(KEY_USER_ADDRESS, "");
    }

    public void setUserAddress(String address) {
        sharedPreferences.edit().putString(KEY_USER_ADDRESS, address).apply();
    }

    public String getUserLastUpdated() {
        return sharedPreferences.getString(KEY_USER_LAST_UPDATED, "");
    }

    public void setDateUpdated(String dateUpdated) {
        sharedPreferences.edit().putString(KEY_USER_LAST_UPDATED, dateUpdated).apply();
    }

    public void clearUserData() {
        sharedPreferences.edit().remove(KEY_USER_ID).apply();
        sharedPreferences.edit().remove(KEY_USER_FULLNAME).apply();
        sharedPreferences.edit().remove(KEY_USER_EMAIL).apply();
        sharedPreferences.edit().remove(KEY_USER_PASSWORD).apply();
        sharedPreferences.edit().remove(KEY_USER_HOBBIES).apply();
        sharedPreferences.edit().remove(KEY_USER_POSTCODE).apply();
        sharedPreferences.edit().remove(KEY_USER_ADDRESS).apply();
        sharedPreferences.edit().remove(KEY_USER_LAST_UPDATED).apply();
    }


    public void setUserDetails(User user) {
        sharedPreferences.edit().putInt(KEY_USER_ID, user.getId()).apply();
        sharedPreferences.edit().putString(KEY_USER_FULLNAME, user.getFullName()).apply();
        sharedPreferences.edit().putString(KEY_USER_EMAIL, user.getEmail()).apply();
        sharedPreferences.edit().putString(KEY_USER_PASSWORD, user.getPassword()).apply();
        sharedPreferences.edit().putString(KEY_USER_HOBBIES, user.getHobbies()).apply();
        sharedPreferences.edit().putString(KEY_USER_POSTCODE, user.getPostcode()).apply();
        sharedPreferences.edit().putString(KEY_USER_ADDRESS, user.getAddress()).apply();
        sharedPreferences.edit().putString(KEY_USER_LAST_UPDATED, user.getDateUpdated()).apply();
    }
}
