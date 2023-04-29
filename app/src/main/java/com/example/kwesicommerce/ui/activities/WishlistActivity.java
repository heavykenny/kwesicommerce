package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.ui.adapters.WishlistRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtils;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        LinearLayout active = findViewById(R.id.linLayoutWishlist);

        NavigationUtils navigationUtils = new NavigationUtils(getBaseContext(), this, active);
        navigationUtils.setNavigationItemClick();

        navigationUtils.backNavigation();

        List<Product> data = new ArrayList<>();
        Category cat = new Category("Gadgets");
        data.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        data.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        data.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));

        RecyclerView rvTestList = findViewById(R.id.rvListWishList);
        WishlistRecyclerViewAdapter adapter = new WishlistRecyclerViewAdapter(data, getApplicationContext());
        rvTestList.setAdapter(adapter);
        rvTestList.setLayoutManager(new LinearLayoutManager(this));
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