package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.CartRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    CartRepository cartRepository;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.setNavigationItemClick();
        navigationUtil.backNavigation("Your Cart");

        cartRepository = new CartRepository(getBaseContext());
        userRepository = new UserRepository(getBaseContext());

        List<CartItem> cartItems = cartRepository.getCartItems(userRepository.getUserId());

        RecyclerView rvCart = findViewById(R.id.rvListCartList);
        LinearLayout layoutCartList = findViewById(R.id.layoutCartList);
        LinearLayout layoutEmptyCart = findViewById(R.id.layoutEmptyCart);

        if (cartItems.isEmpty()) {
            layoutCartList.setVisibility(View.GONE);
            layoutEmptyCart.setVisibility(View.VISIBLE);
        } else {
            layoutCartList.setVisibility(View.VISIBLE);
            layoutEmptyCart.setVisibility(View.GONE);

            CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(cartItems, getApplicationContext());

            rvCart.setAdapter(adapter);
            rvCart.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}