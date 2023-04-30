package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.ui.fragments.AccountFragment;
import com.example.kwesicommerce.utils.NavigationUtil;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        LinearLayout active = findViewById(R.id.linLayoutProfile);
        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("User Account");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragProductContainer, new AccountFragment());
        fragmentTransaction.commit();
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