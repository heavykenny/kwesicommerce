package com.example.kwesicommerce.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.ui.activities.AdminEditCategoryActivity;
import com.example.kwesicommerce.ui.activities.ProductActivity;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    static Context appContext;
    static Activity activity;
    List<Category> categoryList;

    public CategoryRecyclerViewAdapter(List<Category> categoryList, Context appContext, Activity activity) {
        this.categoryList = categoryList;
        CategoryRecyclerViewAdapter.appContext = appContext;
        CategoryRecyclerViewAdapter.activity = activity;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtTitle.setText(categoryList.get(index).getName());
        holder.categoryId = categoryList.get(index).getId();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public int categoryId;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            UserRepository userRepository = new UserRepository(appContext);

            if (userRepository.isUserLoggedIn() && userRepository.getUserAdmin()) {
                Intent intent = new Intent(appContext, AdminEditCategoryActivity.class);
                intent.putExtra("categoryId", categoryId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            } else {
                Intent intent = new Intent(appContext, ProductActivity.class);
                intent.putExtra("categoryId", categoryId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(intent);
                activity.overridePendingTransition(0, 0);
            }
        }
    }
}
