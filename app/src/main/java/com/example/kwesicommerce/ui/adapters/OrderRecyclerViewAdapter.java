package com.example.kwesicommerce.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.CartItemModel;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder> {
    List<CartItemModel> cartItemModelList;
    Context appContext;

    public OrderRecyclerViewAdapter(List<CartItemModel> cartItemModelList, Context appContext) {
        this.cartItemModelList = cartItemModelList;
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public OrderRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_list, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtViewProductListTitle.setText(cartItemModelList.get(index).getProduct().getName());
        holder.txtViewProductPrice.setText(String.format("£%,.2f", cartItemModelList.get(index).getProduct().getPrice()));
        holder.txtViewQuantity.setText(String.format("Quantity: %d", cartItemModelList.get(index).getQuantity()));
        holder.txtViewProductTotalPrice.setText(String.format("Total: £%,.2f", cartItemModelList.get(index).getProduct().getPrice() * cartItemModelList.get(index).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtViewProductListTitle;
        public TextView txtViewProductPrice;
        public TextView txtViewQuantity;
        public TextView txtViewProductTotalPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewProductListTitle = itemView.findViewById(R.id.txtViewProductListTitle);
            txtViewProductPrice = itemView.findViewById(R.id.txtViewProductPrice);
            txtViewQuantity = itemView.findViewById(R.id.txtViewQuantity);
            txtViewProductTotalPrice = itemView.findViewById(R.id.txtViewProductTotalPrice);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
        }
    }
}
