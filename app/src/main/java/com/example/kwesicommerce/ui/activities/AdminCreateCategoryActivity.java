package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CategoryModel;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

public class AdminCreateCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_category);

        NotificationUtil notificationUtil = new NotificationUtil(getBaseContext());

        CategoryRepository categoryRepository = new CategoryRepository(getBaseContext());

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminCategory));
        navigationUtil.setAdminNavigationItemClick();

        navigationUtil.backNavigation("Create Category");

        Button btnCreateCategory = findViewById(R.id.btnCreateCategory);
        btnCreateCategory.setOnClickListener(v -> {
            EditText edtTxtCategoryName = findViewById(R.id.edtTxtCategoryName);

            if (edtTxtCategoryName.getText().toString().isEmpty()) {
                edtTxtCategoryName.setError("Category name is required");
                edtTxtCategoryName.requestFocus();
                return;
            }

            try {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setName(edtTxtCategoryName.getText().toString());

                categoryRepository.createCategory(categoryModel);
                notificationUtil.showToast("Category created successfully", true);
                navigationUtil.goToActivity(AdminManageCategoriesActivity.class);
            } catch (Exception e) {
                notificationUtil.showToast(e.getMessage(), false);
            }
        });
    }
}