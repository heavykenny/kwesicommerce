package com.example.kwesicommerce.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.UserModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.adapters.UserRecyclerViewAdapter;
import com.example.kwesicommerce.utils.NavigationUtil;

import java.util.List;

public class AdminManageUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);

        LinearLayout active = findViewById(R.id.linLayoutAdminUsers);
        NavigationUtil navigationUtil = new NavigationUtil(getBaseContext(), this, active);
        navigationUtil.setAdminNavigationItemClick();
        navigationUtil.setTopNavigationItemClick();

        navigationUtil.backNavigation("Admin Manage Users");

        RecyclerView recyclerViewUserList = findViewById(R.id.recyclerViewUserList);
        UserRepository userRepository = new UserRepository(this);
        List<UserModel> userModelList = userRepository.getAllUsers();

        if (userModelList.isEmpty()) {
            recyclerViewUserList.setVisibility(View.GONE);
        } else {
            recyclerViewUserList.setVisibility(View.VISIBLE);

            UserRecyclerViewAdapter adapter = new UserRecyclerViewAdapter(userModelList, getApplicationContext());

            recyclerViewUserList.setAdapter(adapter);
            recyclerViewUserList.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}