package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.ui.adapters.WishlistRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        LinearLayout active = findViewById(R.id.linLayoutWishlist);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Wishlist");

        WishlistRepository wishlistRepository = new WishlistRepository(getApplicationContext());
        UserRepository userRepository = new UserRepository(getApplicationContext());

        List<ProductModel> wishilistList = new ArrayList<>();

        if (userRepository.isUserLoggedIn()) {
            wishilistList = wishlistRepository.getUserWishlist(userRepository.getUserId());
        }

        RelativeLayout relLayoutWishList = findViewById(R.id.relLayoutWishList);
        RelativeLayout relLayoutWishListEmpty = findViewById(R.id.relLayoutWishListEmpty);

        if (wishilistList.isEmpty()) {
            relLayoutWishListEmpty.setVisibility(LinearLayout.VISIBLE);
            relLayoutWishList.setVisibility(FrameLayout.GONE);
        } else {
            relLayoutWishListEmpty.setVisibility(LinearLayout.GONE);
            relLayoutWishList.setVisibility(FrameLayout.VISIBLE);

            RecyclerView rvTestList = findViewById(R.id.rvListWishList);
            WishlistRecyclerViewAdapter adapter = new WishlistRecyclerViewAdapter(wishilistList, getApplicationContext());
            rvTestList.setAdapter(adapter);
            rvTestList.setLayoutManager(new LinearLayoutManager(this));
        }
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