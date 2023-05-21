package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.CartRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    CartRepository cartRepository;
    UserRepository userRepository;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.setNavigationItemClick();
        navigationUtil.backNavigation("Your Cart");

        NotificationUtil notificationUtil = new NotificationUtil(getBaseContext());

        cartRepository = new CartRepository(getBaseContext());
        userRepository = new UserRepository(getBaseContext());

        List<CartItemModel> userCartItems = cartRepository.getCartItems(userRepository.getUserId());

        RecyclerView rvCart = findViewById(R.id.rvListCartList);
        LinearLayout layoutCartList = findViewById(R.id.layoutCartList);
        LinearLayout layoutEmptyCart = findViewById(R.id.layoutEmptyCart);

        if (userCartItems.isEmpty()) {
            layoutCartList.setVisibility(View.GONE);
            layoutEmptyCart.setVisibility(View.VISIBLE);
        } else {
            layoutCartList.setVisibility(View.VISIBLE);
            layoutEmptyCart.setVisibility(View.GONE);

            CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(userCartItems, getApplicationContext(), this);

            rvCart.setAdapter(adapter);
            rvCart.setLayoutManager(new LinearLayoutManager(this));
        }

        updateTotalPrice();

        Button btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout);
        btnProceedToCheckout.setOnClickListener(v -> {
            if (userCartItems.isEmpty()) {
                notificationUtil.showToast("Your cart is empty", false);
            } else {
                navigationUtil.goToActivity(CheckoutActivity.class);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void updateTotalPrice() {
        TextView txtViewTotalPrice = findViewById(R.id.txtViewTotalPrice);
        txtViewTotalPrice.setText(String.format("TOTAL £%,.2f", cartRepository.getCartTotalPrice(userRepository.getUserId())));
    }
}