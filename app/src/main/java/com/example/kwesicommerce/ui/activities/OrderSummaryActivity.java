package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.repository.CartRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.OrderRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class OrderSummaryActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        CartRepository cartRepository = new CartRepository(getBaseContext());

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("Order Summary");

        UserRepository userRepository = new UserRepository(getBaseContext());

        TextView txtViewName = findViewById(R.id.txtViewName);
        TextView txtViewEmail = findViewById(R.id.txtViewEmail);
        TextView txtViewAddress = findViewById(R.id.txtViewAddress);
        TextView txtViewPostcode = findViewById(R.id.txtViewPostcode);

        txtViewName.setText("Name: " + userRepository.getUserFullName());
        txtViewEmail.setText("Email: " + userRepository.getUserEmail());
        txtViewAddress.setText("Address: " + userRepository.getUser().getAddress());
        txtViewPostcode.setText("Post Code: " + userRepository.getUser().getPostcode());

        TextView txtViewTotalPrice = findViewById(R.id.txtViewTotalPrice);
        txtViewTotalPrice.setText(String.format("TOTAL £%,.2f", cartRepository.getCartTotalPrice(userRepository.getUserId())));

        RecyclerView rvListCartList = findViewById(R.id.rvListCartList);

        cartRepository = new CartRepository(getBaseContext());
        List<CartItemModel> cartItemModels = cartRepository.getCartItems(userRepository.getUserId());
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(cartItemModels, getApplicationContext());

        rvListCartList.setAdapter(adapter);
        rvListCartList.setLayoutManager(new LinearLayoutManager(this));

        TextView btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> {
            CartRepository cartRepository1 = new CartRepository(getBaseContext());
            long orderId = cartRepository1.makeOrder(userRepository.getUserId());
            finish();

            Intent intent = new Intent(this, OrderConfirmationActivity.class);
            intent.putExtra("orderId", orderId + "");
            startActivity(intent);
        });
    }
}