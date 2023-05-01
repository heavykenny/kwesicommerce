package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kwesicommerce.R;

public class AdminEditCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_category);

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra("categoryId", 0);
        Toast.makeText(this, "Category Id: " + categoryId, Toast.LENGTH_SHORT).show();
    }
}