package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.utils.NavigationUtils;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout active = findViewById(R.id.linLayoutProfile);

        NavigationUtils navigationUtils = new NavigationUtils(getBaseContext(), this, active);
        navigationUtils.setNavigationItemClick();

        navigationUtils.backNavigation();
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