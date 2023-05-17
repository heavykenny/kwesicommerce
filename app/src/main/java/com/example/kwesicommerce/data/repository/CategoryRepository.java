package com.example.kwesicommerce.data.repository;

import android.content.Context;

import com.example.kwesicommerce.data.database.SQLiteDatabaseHelper;
import com.example.kwesicommerce.data.model.CategoryModel;

import java.util.List;

public class CategoryRepository {

    private final SQLiteDatabaseHelper dbHelper;

    public CategoryRepository(Context context) {
        dbHelper = new SQLiteDatabaseHelper(context);
    }

    public CategoryModel getCategoryById(int id) {
        return dbHelper.getCategory(id);
    }

    public void createCategory(CategoryModel categoryModel) {
        dbHelper.insertCategory(categoryModel);
    }

    public void updateCategory(CategoryModel categoryModel) {
        dbHelper.updateCategory(categoryModel);
    }

    public void deleteCategory(CategoryModel categoryModel) {
        dbHelper.deleteCategory(categoryModel);
    }

    public List<CategoryModel> getAllCategories() {
        return dbHelper.getCategories();
    }
}