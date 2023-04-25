package com.example.kwesicommerce.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.ui.activities.MainActivity;

public class NavigationUtils {
    public static Context context;
    public static Activity activity;

    public NavigationUtils(Context context, Activity activity) {
        NavigationUtils.context = context;
        NavigationUtils.activity = activity;
    }

    public static void navigateToHomeActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToShopActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToWishlistActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAccountActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToMenuActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void navigate() {
        Button homeButton = activity.findViewById(R.id.home_layout);
        homeButton.setOnClickListener(v -> navigateToHomeActivity());

        Button shopButton = activity.findViewById(R.id.shop_layout);
        shopButton.setOnClickListener(v -> navigateToShopActivity());

        Button wishlistButton = activity.findViewById(R.id.wishlist_layout);
        wishlistButton.setOnClickListener(v -> navigateToWishlistActivity());

        Button accountButton = activity.findViewById(R.id.account_layout);
        accountButton.setOnClickListener(v -> navigateToAccountActivity());

        Button menuButton = activity.findViewById(R.id.menu_layout);
        menuButton.setOnClickListener(v -> navigateToMenuActivity());
    }
}


