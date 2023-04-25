package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.ui.adapters.CategoryRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
}