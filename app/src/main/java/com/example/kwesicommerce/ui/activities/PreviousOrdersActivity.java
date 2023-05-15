package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItem;
import com.example.kwesicommerce.data.repository.OrderRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.OrderRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class PreviousOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this);
        navigationUtil.setTopNavigationItemClick();
        navigationUtil.backNavigation("Previous Orders");

        RecyclerView rvListCartList = findViewById(R.id.rvListCartList);
        UserRepository userRepository = new UserRepository(this);
        OrderRepository orderRepository = new OrderRepository(this);

        int userId = userRepository.getUserId();

        List<CartItem> orderItems = orderRepository.getUsersOrder(userId);
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(orderItems, getApplicationContext());

        rvListCartList.setAdapter(adapter);
        rvListCartList.setLayoutManager(new LinearLayoutManager(this));

        TextView btnBackToShopping = findViewById(R.id.btnBackToShopping);
        btnBackToShopping.setOnClickListener(v -> finish());
    }
}