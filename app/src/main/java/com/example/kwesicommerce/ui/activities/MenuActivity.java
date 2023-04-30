package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        UserRepository userRepository = new UserRepository(getBaseContext());
        NotificationUtil notificationUtil = new NotificationUtil(getBaseContext());

        LinearLayout active = findViewById(R.id.linLayoutMenu);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.setNavigationItemClick();
        navigationUtil.backNavigation("Menu");

        LinearLayout linLayUserLoginCheck = findViewById(R.id.linLayUserLoginCheck);

        if (userRepository.isUserLoggedIn()) {
            linLayUserLoginCheck.setVisibility(LinearLayout.VISIBLE);
        } else {
            linLayUserLoginCheck.setVisibility(LinearLayout.GONE);
        }

        LinearLayout linLayoutAdminAccount = findViewById(R.id.linLayoutAdminAccount);
        if (userRepository.isUserAdmin()) {
            linLayoutAdminAccount.setVisibility(LinearLayout.VISIBLE);
        } else {
            linLayoutAdminAccount.setVisibility(LinearLayout.GONE);
        }

        TextView txtViewLogout = findViewById(R.id.txtViewLogout);
        txtViewLogout.setOnClickListener(v -> {
            userRepository.clearUserData();
            navigationUtil.goToActivity(HomeActivity.class);
        });

        TextView txtViewSwitchToAdminAccount = findViewById(R.id.txtViewSwitchToAdminAccount);
        txtViewSwitchToAdminAccount.setOnClickListener(v -> {
            try {
                userRepository.setUserAdmin(true);
                navigationUtil.goToActivity(HomeActivity.class);
            } catch (Exception e) {
//                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                notificationUtil.showToast(e.getMessage());

            }
        });
    }
}