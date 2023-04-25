package com.example.kwesicommerce.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    List<Category> categoryList;

    Category category;
    Context appContext;

    public CategoryRecyclerViewAdapter(List<Category> categoryList, Context appContext) {
        this.categoryList = categoryList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtTitle.setText(categoryList.get(index).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
        }
    }
}
