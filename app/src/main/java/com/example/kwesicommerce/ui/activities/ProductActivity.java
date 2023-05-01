package com.example.kwesicommerce.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.ui.fragments.ProductFragment;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", 0);

        CategoryRepository categoryRepository = new CategoryRepository(getBaseContext());
        Category category = categoryRepository.getCategoryById(categoryId);

        ProductRepository productRepository = new ProductRepository(getBaseContext());

        List<Product> productList = productRepository.getProductsByCategoryId(categoryId);

        LinearLayout active = findViewById(R.id.linLayoutShop);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation(category.getName() + "'s Product");

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        RelativeLayout relLayoutProductList = findViewById(R.id.relLayoutProductList);
        FrameLayout relLayoutProductContainer = findViewById(R.id.fragProductContainer);
        if (productList.isEmpty()){
            relLayoutProductList.setVisibility(LinearLayout.VISIBLE);
            relLayoutProductContainer.setVisibility(FrameLayout.GONE);
        }else{
            relLayoutProductList.setVisibility(LinearLayout.GONE);
            relLayoutProductContainer.setVisibility(FrameLayout.VISIBLE);
        }
        // Create a new Fragment instance
        ProductFragment fragment = new ProductFragment(productList);
        // Add the Fragment to the layout
        fragmentManager.beginTransaction().add(R.id.fragProductContainer, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}