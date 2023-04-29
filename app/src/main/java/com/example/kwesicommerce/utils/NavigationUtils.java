package com.example.kwesicommerce.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.ui.activities.AccountActivity;
import com.example.kwesicommerce.ui.activities.HomeActivity;
import com.example.kwesicommerce.ui.activities.ProductActivity;
import com.example.kwesicommerce.ui.activities.WishlistActivity;

public class NavigationUtils {
    private final LinearLayout activeNavItem;
    private final Context context;
    private final Activity activity;
    private final LinearLayout homeLayout;
    private final LinearLayout shopLayout;
    private final LinearLayout wishlistLayout;
    private final LinearLayout accountLayout;

    public NavigationUtils(Context context, Activity activity, LinearLayout activeNavItem) {
        this.context = context;
        this.activity = activity;
        this.activeNavItem = activeNavItem;

        homeLayout = activity.findViewById(R.id.home_layout);
        shopLayout = activity.findViewById(R.id.shop_layout);
        wishlistLayout = activity.findViewById(R.id.wishlist_layout);
        accountLayout = activity.findViewById(R.id.account_layout);

    }

    public void setNavigationItemClick() {

        final float scaleValue = 1.2f; // Increase the size by 20%
        final Animation clickAnimation = new ScaleAnimation(1f, scaleValue, 1f, scaleValue,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        clickAnimation.setDuration(200);

        activeNavItem.setBackgroundResource(R.drawable.round_layout_menu);

        homeLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        shopLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, ProductActivity.class);
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
}