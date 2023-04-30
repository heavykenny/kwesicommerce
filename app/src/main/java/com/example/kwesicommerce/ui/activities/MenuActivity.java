package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        LinearLayout linLayoutAdminAccount = findViewById(R.id.linLayoutAdminAccount);
        linLayoutAdminAccount.setVisibility(TextView.GONE);


        TextView txtViewSwitchToUserAccount = findViewById(R.id.txtViewSwitchToUserAccount);

        LinearLayout linLayUserLoginCheck = findViewById(R.id.linLayUserLoginCheck);
        if (userRepository.isUserLoggedIn()) {
            linLayUserLoginCheck.setVisibility(TextView.VISIBLE);
            txtViewSwitchToUserAccount.setVisibility(TextView.GONE);
        } else {
            linLayUserLoginCheck.setVisibility(TextView.GONE);
        }

        TextView txtViewSwitchToAdminAccount = findViewById(R.id.txtViewSwitchToAdminAccount);
        txtViewSwitchToAdminAccount.setOnClickListener(v -> {
            try {
                userRepository.setUserAdmin(true);
                notificationUtil.showToast("Your account has been switched to admin account");
                navigationUtil.goToActivity(AdminHomeActivity.class);
            } catch (Exception e) {
                notificationUtil.showToast(e.getMessage());
            }
        });

        LinearLayout linLayoutUserAccount = findViewById(R.id.linLayoutUserAccount);
        TextView txtViewPreviousOrders = findViewById(R.id.txtViewPreviousOrders);
        if (userRepository.getUserAdmin() && userRepository.isUserLoggedIn()) {
            linLayoutAdminAccount.setVisibility(TextView.VISIBLE);
            linLayoutUserAccount.setVisibility(TextView.GONE);
            txtViewPreviousOrders.setVisibility(TextView.GONE);
            txtViewSwitchToUserAccount.setVisibility(TextView.VISIBLE);
            txtViewSwitchToAdminAccount.setVisibility(TextView.GONE);
        }


        txtViewSwitchToUserAccount.setOnClickListener(v -> {
            try {
                userRepository.setUserAdmin(false);
                notificationUtil.showToast("Your account has been switched to user account");
                navigationUtil.goToActivity(HomeActivity.class);
            } catch (Exception e) {
                notificationUtil.showToast(e.getMessage());
            }
        });


        TextView txtViewLogout = findViewById(R.id.txtViewLogout);
        txtViewLogout.setOnClickListener(v -> {
            userRepository.clearUserData();
            navigationUtil.goToActivity(HomeActivity.class);
        });

        TextView txtViewDashboard = findViewById(R.id.txtViewDashboard);
        txtViewDashboard.setOnClickListener(v -> {
            navigationUtil.goToActivity(AdminHomeActivity.class);
        });
    }
}