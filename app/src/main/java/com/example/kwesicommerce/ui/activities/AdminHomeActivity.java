package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.repository.CategoryRepository;
import com.example.kwesicommerce.data.repository.OrderRepository;
import com.example.kwesicommerce.data.repository.ProductRepository;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.utils.NavigationUtil;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, findViewById(R.id.linLayoutAdminHome));
        navigationUtil.setAdminNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Admin Dashboard");

        ProductRepository productRepository = new ProductRepository(getBaseContext());

        TextView txtProductsCount = findViewById(R.id.txtProductsCount);
        txtProductsCount.setText(String.valueOf(productRepository.getAllProducts().size()));


        CategoryRepository categoryRepository = new CategoryRepository(getBaseContext());
        TextView txtCategoriesCount = findViewById(R.id.txtCategoriesCount);
        txtCategoriesCount.setText(String.valueOf(categoryRepository.getAllCategories().size()));

        TextView txtAdminsCount = findViewById(R.id.txtAdminsCount);
        UserRepository userRepository = new UserRepository(getBaseContext());
        txtAdminsCount.setText(String.valueOf(userRepository.getAllAdmins().size()));

        TextView txtCustomersCount = findViewById(R.id.txtCustomersCount);
        txtCustomersCount.setText(String.valueOf(userRepository.getAllCustomers().size()));

        TextView txtOrdersCount = findViewById(R.id.txtOrdersCount);
        TextView txtSalesCount = findViewById(R.id.txtSalesCount);
        OrderRepository orderRepository = new OrderRepository(getBaseContext());
        int ordersCount = orderRepository.getAllOrders().size();
        txtOrdersCount.setText(String.valueOf(ordersCount));
        txtSalesCount.setText(String.valueOf(orderRepository.getTotalSales()));
    }
}