package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.utils.NavigationUtil;

public class AdminCreateCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_category);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminCategory));
        navigationUtil.setAdminNavigationItemClick();
    }
}