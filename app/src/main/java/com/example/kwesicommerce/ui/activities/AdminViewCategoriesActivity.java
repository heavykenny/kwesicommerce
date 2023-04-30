package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.utils.NavigationUtil;

public class AdminViewCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_categories);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminCategory));
        navigationUtil.setAdminNavigationItemClick();
    }
}