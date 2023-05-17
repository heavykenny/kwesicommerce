package com.example.kwesicommerce.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItemModel;
import com.example.kwesicommerce.data.repository.OrderRepository;
import com.example.kwesicommerce.ui.adapters.OrderRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class AdminManageOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_orders);

        LinearLayout active = findViewById(R.id.linLayoutAdminOrders);
        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setAdminNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Admin Manage Orders");

        OrderRepository orderRepository = new OrderRepository(this);
        List<CartItemModel> orderItems = orderRepository.getAllOrdersProduct();

        RelativeLayout relLayoutNoOrders  = findViewById(R.id.relLayoutNoOrders);
        RelativeLayout relLayoutAllOrders = findViewById(R.id.relLayoutAllOrders);

        if (orderItems.isEmpty()) {
            relLayoutNoOrders.setVisibility(RelativeLayout.VISIBLE);
            relLayoutAllOrders.setVisibility(RelativeLayout.GONE);
        } else {
            relLayoutNoOrders.setVisibility(RelativeLayout.GONE);
            relLayoutAllOrders.setVisibility(RelativeLayout.VISIBLE);
        }

        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(orderItems, getApplicationContext());
        RecyclerView rvListCartList = findViewById(R.id.rvListCartList);
        rvListCartList.setAdapter(adapter);
        rvListCartList.setLayoutManager(new LinearLayoutManager(this));
    }
}