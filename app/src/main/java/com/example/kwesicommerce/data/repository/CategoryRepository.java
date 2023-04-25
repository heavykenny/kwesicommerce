package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDBHelper;
import com.example.kwesicommerce.data.model.Category;

import java.util.List;

public class CategoryRepository {

    private final SQLiteDBHelper dbHelper;

    public CategoryRepository(Context context) {
        dbHelper = new SQLiteDBHelper(context);
    }

    public Category getCategoryById(int id) {
        return dbHelper.getCategory(id);
    }

    public void createCategory(Category category) {
        dbHelper.insertCategory(category);
    }

    public void updateCategory(Category category) {
        dbHelper.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        dbHelper.deleteCategory(category);
    }

    public List<Category> getAllCategories() {
        return dbHelper.getCategories();
    }
}