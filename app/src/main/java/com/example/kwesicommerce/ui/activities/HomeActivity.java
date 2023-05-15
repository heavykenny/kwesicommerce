package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LinearLayout active = findViewById(R.id.linLayoutHome);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        UserRepository userRepository = new UserRepository(getBaseContext());
        if (userRepository.isUserLoggedIn() && userRepository.getUserAdmin()) {
            navigationUtil.goToActivity(AdminHomeActivity.class);
        }

        Button buttonShopNow = findViewById(R.id.buttonShopNow);
        buttonShopNow.setOnClickListener(v -> navigationUtil.goToActivity(CategoryActivity.class));
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