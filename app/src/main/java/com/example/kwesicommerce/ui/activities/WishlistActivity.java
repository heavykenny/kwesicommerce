package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.ui.adapters.CategoryRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtils;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        LinearLayout active = findViewById(R.id.wishlist_layout);

        NavigationUtils navigationUtils = new NavigationUtils(getBaseContext(), this, active);
        navigationUtils.setNavigationItemClick();

        List<Category> data = new ArrayList<>();
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));
        data.add(new Category("Food"));

        RecyclerView rvTestList = findViewById(R.id.rvTestList);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(data, getApplicationContext());
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