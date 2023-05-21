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
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.ui.activities.AccountActivity;
import com.example.kwesicommerce.ui.activities.AdminHomeActivity;
import com.example.kwesicommerce.ui.activities.AdminManageCategoriesActivity;
import com.example.kwesicommerce.ui.activities.AdminManageOrdersActivity;
import com.example.kwesicommerce.ui.activities.AdminManageProductsActivity;
import com.example.kwesicommerce.ui.activities.AdminManageUserActivity;
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

    private final TextView txtViewWishlistCounter;
    UserRepository userRepository;
    CartRepository cartRepository;

    WishlistRepository wishlistRepository;
    // admin navigation
    private LinearLayout adminHomeLayout = null;
    private LinearLayout adminCategoryLayout = null;
    private LinearLayout adminProductLayout = null;
    private LinearLayout adminOrdersLayout = null;
    private LinearLayout linLayoutAdminUsers = null;
    private LinearLayout adminMenuLayout = null;

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
        txtViewWishlistCounter = activity.findViewById(R.id.txtViewWishlistCounter);


        // admin navigation
        adminHomeLayout = activity.findViewById(R.id.linLayoutAdminHome);
        adminCategoryLayout = activity.findViewById(R.id.linLayoutAdminCategory);
        adminProductLayout = activity.findViewById(R.id.linLayoutAdminProduct);
        adminOrdersLayout = activity.findViewById(R.id.linLayoutAdminOrders);
        linLayoutAdminUsers = activity.findViewById(R.id.linLayoutAdminUsers);
        adminMenuLayout = activity.findViewById(R.id.linLayoutAdminMenu);

        txtViewCartCounter = activity.findViewById(R.id.txtViewCartCounter);
        clickAnimation.setDuration(200);

        userRepository = new UserRepository(context);
        cartRepository = new CartRepository(context);
        wishlistRepository = new WishlistRepository(context);
    }

    public NavigationUtil(Context baseContext, Activity activity) {
        this.context = baseContext;
        this.activity = activity;
        this.activeNavItem = null;

        homeLayout = activity.findViewById(R.id.linLayoutHome);
        categoryLayout = activity.findViewById(R.id.linLayoutShop);
        wishlistLayout = activity.findViewById(R.id.linLayoutWishlist);
        accountLayout = activity.findViewById(R.id.linLayoutProfile);
        menuLayout = activity.findViewById(R.id.linLayoutMenu);
        txtViewCartCounter = activity.findViewById(R.id.txtViewCartCounter);
        txtViewWishlistCounter = activity.findViewById(R.id.txtViewWishlistCounter);

        imgViewShopIcon = activity.findViewById(R.id.imgViewShopIcon);
        imgViewSearchIcon = activity.findViewById(R.id.imgViewSearchIcon);
        clickAnimation.setDuration(200);

        userRepository = new UserRepository(context);
        cartRepository = new CartRepository(context);
        wishlistRepository = new WishlistRepository(context);
    }

    /**
     * Set navigation item click
     */
    public void setNavigationItemClick() {
        // set wishlist count
        if (userRepository.isUserLoggedIn()) {
            txtViewWishlistCounter.setText(
                    String.valueOf(wishlistRepository.getUserWishlistCount(userRepository.getUserId()))
            );
        } else {
            txtViewWishlistCounter.setText("0");
        }

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

        // set user cart count
        if (userRepository.isUserLoggedIn()) {
            txtViewCartCounter.setText(
                    String.valueOf(cartRepository.getCartItems(userRepository.getUserId()).size())
            );
        } else {
            txtViewCartCounter.setText("0");
        }

        // set wishlist event listener
        imgViewShopIcon.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, CartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        // set search event listener
        imgViewSearchIcon.setOnClickListener(v -> {
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Set back navigation to previous activity
     *
     * @param pageTitle
     */
    public void backNavigation(String pageTitle) {
        // set back button event listener
        RelativeLayout backLayout = activity.findViewById(R.id.relLayoutBackButton);
        TextView txtViewPageTitleButton = activity.findViewById(R.id.txtViewPageTitle);

        txtViewPageTitleButton.setText(pageTitle);
        backLayout.setOnClickListener(v -> {
            activity.finish();
            activity.overridePendingTransition(0, 0);
        });
    }

    public void goToActivity(Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    public void setAdminNavigationItemClick() {
        if (activeNavItem != null) {
            activeNavItem.setBackgroundResource(R.color.oxford_blue_dark);
        }

        adminHomeLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AdminHomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        adminCategoryLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AdminManageCategoriesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        adminProductLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AdminManageProductsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        linLayoutAdminUsers.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AdminManageUserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        adminOrdersLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, AdminManageOrdersActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });

        adminMenuLayout.setOnClickListener(v -> {
            v.startAnimation(clickAnimation);
            Intent intent = new Intent(context, MenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            activity.overridePendingTransition(0, 0);
        });
    }
}