package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.ui.adapters.CategoryRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        LinearLayout active = findViewById(R.id.linLayoutShop);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Category");

        CategoryRepository categoryRepository = new CategoryRepository(getApplicationContext());

        List<Category> categoryList = categoryRepository.getAllCategories();
        RecyclerView rvListCategory = findViewById(R.id.rvListCategoryList);
        LinearLayout layoutEmptyCategory = findViewById(R.id.layoutEmptyCategory);

        if (categoryList.isEmpty()) {
            rvListCategory.setVisibility(View.GONE);
            layoutEmptyCategory.setVisibility(View.VISIBLE);
        } else {
            rvListCategory.setVisibility(View.VISIBLE);
            layoutEmptyCategory.setVisibility(View.GONE);

            CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(categoryList, getApplicationContext(), this);
            rvListCategory.setAdapter(adapter);
            rvListCategory.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}