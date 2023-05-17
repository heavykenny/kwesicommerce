package com.example.kwesicommerce.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.ui.fragments.ProductFragment;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminManageProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_products);

        ProductRepository productRepository = new ProductRepository(getBaseContext());

        List<ProductModel> productModelList = productRepository.getAllProducts();

        LinearLayout active = findViewById(R.id.linLayoutAdminProduct);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setAdminNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Admin Manage Products");

        FloatingActionButton fabAddProduct = findViewById(R.id.fabAddProduct);
        fabAddProduct.setOnClickListener(v -> {
            Intent i = new Intent(getBaseContext(), AdminCreateProductActivity.class);
            startActivity(i);
        });

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        RelativeLayout relLayoutProductList = findViewById(R.id.relLayoutProductList);
        FrameLayout relLayoutProductContainer = findViewById(R.id.fragProductContainer);
        if (productModelList.isEmpty()) {


            relLayoutProductList.setVisibility(LinearLayout.VISIBLE);
            relLayoutProductContainer.setVisibility(FrameLayout.GONE);
        } else {

            relLayoutProductList.setVisibility(LinearLayout.GONE);
            relLayoutProductContainer.setVisibility(FrameLayout.VISIBLE);
            // Create a new Fragment instance
            ProductFragment fragment = new ProductFragment(productModelList);
            // Add the Fragment to the layout
            fragmentManager.beginTransaction().add(R.id.fragProductContainer, fragment)
                    .commit();
        }
    }
}