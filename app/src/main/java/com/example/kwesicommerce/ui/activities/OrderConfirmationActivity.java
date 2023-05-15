package com.example.kwesicommerce.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.repository.OrderRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.OrderRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class OrderConfirmationActivity extends AppCompatActivity {
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");

        OrderRepository orderRepository = new OrderRepository(getBaseContext());

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("Order Confirmation");

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
        txtViewTotalPrice.setText(String.format("TOTAL £%,.2f", orderRepository.getOrderTotalPrice(orderId)));

        RecyclerView rvListCartList = findViewById(R.id.rvListCartList);

        List<CartItem> orderItems = orderRepository.getOrderItems(orderId);
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(orderItems, getApplicationContext());

        rvListCartList.setAdapter(adapter);
        rvListCartList.setLayoutManager(new LinearLayoutManager(this));

        TextView btnBackToShopping = findViewById(R.id.btnBackToShopping);
        btnBackToShopping.setOnClickListener(v -> navigationUtil.goToActivity(CategoryActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.goToActivity(CategoryActivity.class);
    }
}