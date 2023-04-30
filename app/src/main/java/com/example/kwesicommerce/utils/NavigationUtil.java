package com.example.kwesicommerce.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.activities.AccountActivity;
import com.example.kwesicommerce.ui.activities.CartActivity;
import com.example.kwesicommerce.ui.activities.CategoryActivity;
import com.example.kwesicommerce.ui.activities.HomeActivity;
import com.example.kwesicommerce.ui.activities.MenuActivity;
import com.example.kwesicommerce.ui.activities.WishlistActivity;

public class NavigationUtil {
    final float scaleValue = 1.2f;
    private final Animation clickAnimation = new ScaleAnimation(1f, scaleValue, 1f, scaleValue,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    private final LinearLayout activeNavItem;
    private final Context context;
    private final Activity activity;
    private final LinearLayout homeLayout;
    private final LinearLayout categoryLayout;
    private final LinearLayout wishlistLayout;
    private final LinearLayout accountLayout;
    private final LinearLayout menuLayout;
    private final ImageView imgViewShopIcon;
    private final ImageView imgViewSearchIcon;
    private final TextView txtViewCartCounter;

    UserRepository userRepository;
    CartRepository cartRepository;

    public NavigationUtil(Context context, Activity activity, LinearLayout activeNavItem) {
        this.context = context;
        this.activity = activity;
        this.activeNavItem = activeNavItem;

        homeLayout = activity.findViewById(R.id.linLayoutHome);
        categoryLayout = activity.findViewById(R.id.linLayoutShop);
        wishlistLayout = activity.findViewById(R.id.linLayoutWishlist);
        accountLayout = activity.findViewById(R.id.linLayoutProfile);
        menuLayout = activity.findViewById(R.id.linLayoutMenu);
        imgViewShopIcon = activity.findViewById(R.id.imgViewShopIcon);
        imgViewSearchIcon = activity.findViewById(R.id.imgViewSearchIcon);

        txtViewCartCounter = activity.findViewById(R.id.txtViewCartCounter);
        clickAnimation.setDuration(200);

        userRepository = new UserRepository(context);
        cartRepository = new CartRepository(context);
    }

    public NavigationUtil(Context baseContext, CartActivity activity) {
        this.context = baseContext;
        this.activity = activity;
        this.activeNavItem = null;

        homeLayout = activity.findViewById(R.id.linLayoutHome);
        categoryLayout = activity.findViewById(R.id.linLayoutShop);
        wishlistLayout = activity.findViewById(R.id.linLayoutWishlist);
        accountLayout = activity.findViewById(R.id.linLayoutProfile);
        menuLayout = activity.findViewById(R.id.linLayoutMenu);
        txtViewCartCounter = activity.findViewById(R.id.txtViewCartCounter);

        imgViewShopIcon = activity.findViewById(R.id.imgViewShopIcon);
        imgViewSearchIcon = activity.findViewById(R.id.imgViewSearchIcon);
        clickAnimation.setDuration(200);

        userRepository = new UserRepository(context);
        cartRepository = new CartRepository(context);
    }

    public void setNavigationItemClick() {
        if (activeNavItem != null) {
            activeNavItem.setBackgroundResource(R.color.oxford_blue_dark);
        }

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

        menuLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });
    }

    public void setTopNavigationItemClick() {

        if (userRepository.isUserLoggedIn()) {
            txtViewCartCounter.setText(String.valueOf(cartRepository.getCartTotalQuantity(userRepository.getUserId())));
        } else {
            txtViewCartCounter.setText("0");
        }

        imgViewShopIcon.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, CartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        imgViewSearchIcon.setOnClickListener(v -> {
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        });
    }

    public void backNavigation(String pageTitle) {

        RelativeLayout backLayout = activity.findViewById(R.id.relLayoutBackButton);
        TextView txtViewPageTitleButton = activity.findViewById(R.id.txtViewPageTitle);

        txtViewPageTitleButton.setText(pageTitle);
        backLayout.setOnClickListener(v -> {
            activity.finish();
            activity.overridePendingTransition(0, 0);
        });
    }

    public void goToActivity(Class loginActivityClass) {
        Intent intent = new Intent(context, loginActivityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }
}