package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.CarouselPagerAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

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

        // Image Slider
        ViewPager viewPageImageSlider = findViewById(R.id.viewPageImageSlider);

        // Image List for Image Slider
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.feature_prod_01);
        imageList.add(R.drawable.feature_prod_02);
        imageList.add(R.drawable.feature_prod_03);

        // Image Slider Adapter and Set Adapter
        CarouselPagerAdapter carouselAdapter;
        carouselAdapter = new CarouselPagerAdapter(this, imageList);
        viewPageImageSlider.setAdapter(carouselAdapter);
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