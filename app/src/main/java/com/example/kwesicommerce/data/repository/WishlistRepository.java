package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.sharedpreferences.SharedPreferencesHelper;

import java.util.List;

public class WishlistRepository {
    private final SQLiteDBHelper dbHelper;
    private final SharedPreferencesHelper preferencesHelper;


    public WishlistRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
        preferencesHelper = new SharedPreferencesHelper(context);
    }

    public int addToWishlist(int productId, int userId) {
        return dbHelper.insertWishlist(productId, userId);
    }

    public List<Product> getUserWishlist(int userId) {
        return dbHelper.getUserWishlist(userId);
    }

    public void removeProductFromWishlist(int productId, int userId) {
        dbHelper.deleteWishlist(productId, userId);
    }

    // get wishlist count for user
    public int getUserWishlistCount(int userId) {
        return dbHelper.getUserWishlistCount(userId);
    }
}
