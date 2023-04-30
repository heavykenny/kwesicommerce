package com.example.kwesicommerce.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Product;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class WishlistRecyclerViewAdapter extends RecyclerView.Adapter<WishlistRecyclerViewAdapter.ViewHolder> {
    List<Product> productList;
    Product product;
    Context appContext;

    public WishlistRecyclerViewAdapter(List<Product> productList, Context appContext) {
        this.productList = productList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public WishlistRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wishishlist_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtTitle.setText(productList.get(index).getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtViewProductListTitle);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
        }
    }
}
