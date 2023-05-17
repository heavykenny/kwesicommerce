package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.ProductModel;

import java.util.List;

public class ProductRepository {

    private SQLiteDatabaseHelper dbHelper;

    public ProductRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
    }

    public ProductModel getProductById(int id) {
        return dbHelper.getProduct(id);
    }

    public int createProduct(ProductModel productModel) {
        return dbHelper.insertProduct(productModel);
    }

    public void updateProduct(ProductModel productModel) {
        dbHelper.updateProduct(productModel);
    }

    public void deleteProduct(ProductModel productModel) {
        dbHelper.deleteProduct(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return dbHelper.getProducts();
    }

    public List<ProductModel> getProductsByCategoryId(int categoryId) {
        return dbHelper.getProductsByCategoryId(categoryId);
    }
}
