package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.ui.fragments.ProductFragment;
import com.example.kwesicommerce.utils.NavigationUtil;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        LinearLayout active = findViewById(R.id.linLayoutShop);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Products");

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create a new Fragment instance
        ProductFragment fragment = new ProductFragment();
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