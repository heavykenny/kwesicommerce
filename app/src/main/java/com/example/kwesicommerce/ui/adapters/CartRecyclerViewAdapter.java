package com.example.kwesicommerce.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Cart;
import com.example.kwesicommerce.data.model.CartItem;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {
    List<Cart> cartList;
    Context appContext;
    private final List<CartItem> cartItemList;
    private final Context context;

    public CartRecyclerViewAdapter(List<CartItem> cartItemList, Context context) {
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtViewProductListTitle;
        private Button btnQuantityCounter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewProductListTitle = itemView.findViewById(R.id.txtViewProductListTitle);
        }

        public void bind(CartItem cartItem) {
            txtViewProductListTitle.setText(cartItem.getProduct().getName());
        }
    }
}