package com.example.kwesicommerce.data.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kwesicommerce.data.model.UserModel;

public class SharedPreferencesHelper {

    private static final String PREF_NAME = "kwesi_commerce_pref";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_FULLNAME = "user_fullname";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_HOBBIES = "user_hobbies";
    private static final String KEY_USER_POSTCODE = "user_postcode";
    private static final String KEY_USER_ADDRESS = "user_address";
    private static final String KEY_USER_PROFILE_IMAGE = "user_profile_image";
    private static final String KEY_USER_LAST_UPDATED = "user_last_updated";
    private static final String KEY_USER_IS_ADMIN = "user_is_admin";


    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }

    public String getUserFullName() {
        return sharedPreferences.getString(KEY_USER_FULLNAME, "");
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public String getUserHobbies() {
        return sharedPreferences.getString(KEY_USER_HOBBIES, "");
    }

    public String getUserPostcode() {
        return sharedPreferences.getString(KEY_USER_POSTCODE, "");
    }

    public String getUserAddress() {
        return sharedPreferences.getString(KEY_USER_ADDRESS, "");
    }

    public void clearUserData() {
        sharedPreferences.edit().remove(KEY_USER_ID).apply();
        sharedPreferences.edit().remove(KEY_USER_FULLNAME).apply();
        sharedPreferences.edit().remove(KEY_USER_EMAIL).apply();
        sharedPreferences.edit().remove(KEY_USER_HOBBIES).apply();
        sharedPreferences.edit().remove(KEY_USER_POSTCODE).apply();
        sharedPreferences.edit().remove(KEY_USER_ADDRESS).apply();
        sharedPreferences.edit().remove(KEY_USER_PROFILE_IMAGE).apply();
        sharedPreferences.edit().remove(KEY_USER_LAST_UPDATED).apply();
    }

    public void setUserDetails(UserModel userModel) {
        sharedPreferences.edit().putInt(KEY_USER_ID, userModel.getId()).apply();
        sharedPreferences.edit().putString(KEY_USER_FULLNAME, userModel.getFullName()).apply();
        sharedPreferences.edit().putString(KEY_USER_EMAIL, userModel.getEmail()).apply();
        sharedPreferences.edit().putString(KEY_USER_HOBBIES, userModel.getHobbies()).apply();
        sharedPreferences.edit().putString(KEY_USER_POSTCODE, userModel.getPostcode()).apply();
        sharedPreferences.edit().putString(KEY_USER_ADDRESS, userModel.getAddress()).apply();
        sharedPreferences.edit().putString(KEY_USER_PROFILE_IMAGE, userModel.getProfileImage()).apply();
        sharedPreferences.edit().putString(KEY_USER_LAST_UPDATED, userModel.getDateUpdated()).apply();
        sharedPreferences.edit().putBoolean(KEY_USER_IS_ADMIN, userModel.isAdmin()).apply();
    }

    public boolean getUserAdmin() {
        return sharedPreferences.getBoolean(KEY_USER_IS_ADMIN, false);
    }

    public void setUserAdmin(boolean value) {
        sharedPreferences.edit().putBoolean(KEY_USER_IS_ADMIN, value).apply();
    }

    public String getUserProfileImage() {
        return sharedPreferences.getString(KEY_USER_PROFILE_IMAGE, "");
    }
}
