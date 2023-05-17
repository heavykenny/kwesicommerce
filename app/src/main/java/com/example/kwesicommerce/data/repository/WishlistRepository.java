package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.ProductModel;

import java.util.List;

public class WishlistRepository {
    private final SQLiteDatabaseHelper dbHelper;

    public WishlistRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
    }

    public int addToWishlist(int productId, int userId) {
        return dbHelper.insertWishlist(productId, userId);
    }

    public List<ProductModel> getUserWishlist(int userId) {
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
