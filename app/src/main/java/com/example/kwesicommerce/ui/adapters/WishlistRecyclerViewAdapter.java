package com.example.kwesicommerce.ui.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.data.model.User;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class WishlistRecyclerViewAdapter extends RecyclerView.Adapter<WishlistRecyclerViewAdapter.ViewHolder> {
    static List<Product> productList;
    Context appContext;

    WishlistRepository wishlistRepository;

    UserRepository userRepository;

    NotificationUtil notificationUtil;

    public WishlistRecyclerViewAdapter(List<Product> productList, Context appContext) {
        WishlistRecyclerViewAdapter.productList = productList;
        this.appContext = appContext;
        wishlistRepository = new WishlistRepository(appContext);
        userRepository = new UserRepository(appContext);
        notificationUtil = new NotificationUtil(appContext);
    }

    @NonNull
    @Override
    public WishlistRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wishishlist_list, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WishlistRecyclerViewAdapter.ViewHolder holder, int index) {
        holder.txtTitle.setText(productList.get(index).getName());
        holder.txtViewProductPrice.setText("£ " + productList.get(index).getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public TextView txtViewProductPrice;
        public Button btnWishListRemoveCart;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtViewProductListTitle);
            txtViewProductPrice = itemView.findViewById(R.id.txtViewProductPrice);
            btnWishListRemoveCart = itemView.findViewById(R.id.btnWishListRemoveCart);

            btnWishListRemoveCart.setOnClickListener(v -> {
                int index = getAdapterPosition();
                Toast.makeText(appContext, "" +productList.get(index).getName(), Toast.LENGTH_SHORT).show();
                wishlistRepository.removeProductFromWishlist(productList.get(index).getId(), userRepository.getUserId());
                productList.remove(index);
                notifyItemRemoved(index);

                notificationUtil.showToast("Product removed from wishlist");
            });

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
        }
    }
}
