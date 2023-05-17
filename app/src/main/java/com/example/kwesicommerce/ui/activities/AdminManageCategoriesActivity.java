package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CategoryModel;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.ui.adapters.CategoryRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminManageCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_categories);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminCategory));
        navigationUtil.setAdminNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("Admin Manage Categories");


        FloatingActionButton fabAddCategory = findViewById(R.id.fabAddCategory);
        fabAddCategory.setOnClickListener(v -> {
            navigationUtil.goToActivity(AdminCreateCategoryActivity.class);
        });


        CategoryRepository categoryRepository = new CategoryRepository(getApplicationContext());
        List<CategoryModel> categoryModelList = categoryRepository.getAllCategories();
        RecyclerView rvListCategory = findViewById(R.id.rvListCategoryList);
        RelativeLayout relLayoutCategoryList = findViewById(R.id.relLayoutCategoryList);

        if (categoryModelList.isEmpty()) {
            rvListCategory.setVisibility(View.GONE);
            relLayoutCategoryList.setVisibility(View.VISIBLE);
        } else {
            rvListCategory.setVisibility(View.VISIBLE);
            relLayoutCategoryList.setVisibility(View.GONE);

            CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categoryModelList, getApplicationContext(), this);
            rvListCategory.setAdapter(adapter);
            rvListCategory.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}