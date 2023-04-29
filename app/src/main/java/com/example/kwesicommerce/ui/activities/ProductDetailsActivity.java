
package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.utils.NavigationUtils;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        LinearLayout active = findViewById(R.id.linLayoutShop);

        NavigationUtils navigationUtils = new NavigationUtils(getBaseContext(), this, active);
        navigationUtils.setNavigationItemClick();

        navigationUtils.backNavigation("Product Details");
    }
}