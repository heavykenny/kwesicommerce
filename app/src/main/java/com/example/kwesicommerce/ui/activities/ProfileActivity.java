package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtils;

public class ProfileActivity extends AppCompatActivity {

    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LinearLayout active = findViewById(R.id.linLayoutProfile);

        NavigationUtils navigationUtils = new NavigationUtils(getBaseContext(), this, active);
        navigationUtils.setNavigationItemClick();

        navigationUtils.backNavigation("User Profile");
        userRepository = new UserRepository(getBaseContext());

        TextView txtViewFirstName = findViewById(R.id.txtProfileName);
        TextView txtViewEmail = findViewById(R.id.txtProfileEmail);
        TextView txtProfileAddress = findViewById(R.id.txtProfileAddress);
        TextView txtProfilePhoneNumber = findViewById(R.id.txtProfilePhoneNumber);

        txtViewFirstName.setText(userRepository.getUserFullName());
        txtViewEmail.setText(userRepository.getUserEmail());
        txtProfileAddress.setText(userRepository.getUserAddress());
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