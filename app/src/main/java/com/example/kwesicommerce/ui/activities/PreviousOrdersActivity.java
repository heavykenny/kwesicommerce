package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.RelativeLayout;
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

        RelativeLayout relLayoutPreviousOrdersEmpty = findViewById(R.id.relLayoutPreviousOrdersEmpty);
        RelativeLayout relLayoutPreviousOrders = findViewById(R.id.relLayoutPreviousOrders);

        if (orderItems.isEmpty()) {
            relLayoutPreviousOrdersEmpty.setVisibility(RelativeLayout.VISIBLE);
            relLayoutPreviousOrders.setVisibility(RelativeLayout.GONE);
        } else {
            relLayoutPreviousOrdersEmpty.setVisibility(RelativeLayout.GONE);
            relLayoutPreviousOrders.setVisibility(RelativeLayout.VISIBLE);
        }

        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(orderItems, getApplicationContext());

        rvListCartList.setAdapter(adapter);
        rvListCartList.setLayoutManager(new LinearLayoutManager(this));

        TextView btnBackToShopping = findViewById(R.id.btnBackToShopping);
        btnBackToShopping.setOnClickListener(v -> {
            navigationUtil.goToActivity(CategoryActivity.class);
        });
    }
}