package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.Product;

import java.util.List;

public class ProductRepository {

    private SQLiteDBHelper dbHelper;

    public ProductRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
    }

    public Product getProductById(int id) {
        return dbHelper.getProduct(id);
    }

    public void createProduct(Product product) {
        dbHelper.insertProduct(product);
    }

    public void updateProduct(Product product) {
        dbHelper.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        dbHelper.deleteProduct(product);
    }

    public List<Product> getAllProducts() {
        return dbHelper.getProducts();
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        return dbHelper.getProductsByCategoryId(categoryId);
    }
}
