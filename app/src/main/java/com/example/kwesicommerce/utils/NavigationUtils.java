package com.example.kwesicommerce.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.ui.activities.AccountActivity;
import com.example.kwesicommerce.ui.activities.CategoryActivity;
import com.example.kwesicommerce.ui.activities.HomeActivity;
import com.example.kwesicommerce.ui.activities.WishlistActivity;

public class NavigationUtils {
    private final LinearLayout activeNavItem;
    private final Context context;
    private final Activity activity;
    private final LinearLayout homeLayout;
    private final LinearLayout categoryLayout;
    private final LinearLayout wishlistLayout;
    private final LinearLayout accountLayout;

    public NavigationUtils(Context context, Activity activity, LinearLayout activeNavItem) {
        this.context = context;
        this.activity = activity;
        this.activeNavItem = activeNavItem;

        homeLayout = activity.findViewById(R.id.linLayoutHome);
        categoryLayout = activity.findViewById(R.id.linLayoutShop);
        wishlistLayout = activity.findViewById(R.id.linLayoutWishlist);
        accountLayout = activity.findViewById(R.id.linLayoutProfile);

    }

    public void setNavigationItemClick() {

        final float scaleValue = 1.2f; // Increase the size by 20%
        final Animation clickAnimation = new ScaleAnimation(1f, scaleValue, 1f, scaleValue,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickAnimation.setDuration(200);

        activeNavItem.setBackgroundResource(R.drawable.custom_round_layout);

        homeLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        categoryLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, CategoryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        wishlistLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, WishlistActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        accountLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AccountActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });
    }

    public void backNavigation() {

        RelativeLayout backLayout = activity.findViewById(R.id.relLayoutBackButton);
        backLayout.setOnClickListener(v -> {
            activity.finish();
            activity.overridePendingTransition(0, 0);
        });
    }
}