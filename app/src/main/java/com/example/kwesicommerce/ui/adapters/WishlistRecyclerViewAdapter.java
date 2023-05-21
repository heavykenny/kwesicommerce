package com.example.kwesicommerce.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

// REFERENCE: https://developer.android.com/develop/ui/views/layout/recyclerview
public class WishlistRecyclerViewAdapter extends RecyclerView.Adapter<WishlistRecyclerViewAdapter.ViewHolder> {
    static List<ProductModel> productModelList;
    Context appContext;

    WishlistRepository wishlistRepository;

    UserRepository userRepository;

    NotificationUtil notificationUtil;

    public WishlistRecyclerViewAdapter(List<ProductModel> productModelList, Context appContext) {
        WishlistRecyclerViewAdapter.productModelList = productModelList;
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
        holder.txtTitle.setText(productModelList.get(index).getName());
        holder.txtViewProductPrice.setText("£ " + productModelList.get(index).getPrice());
        Glide.with(holder.itemView)
                .load(productModelList.get(index).getImageUrl())
                .override(150, 150)
                .centerCrop()
                .into(holder.imgViewProductListImage);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtTitle;
        public TextView txtViewProductPrice;
        public Button btnWishListRemoveCart;

        public ImageView imgViewProductListImage;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtViewProductListTitle);
            txtViewProductPrice = itemView.findViewById(R.id.txtViewProductPrice);
            btnWishListRemoveCart = itemView.findViewById(R.id.btnWishListRemoveCart);
            btnWishListRemoveCart.setOnClickListener(v -> {
                int index = getAdapterPosition();
                wishlistRepository.removeProductFromWishlist(productModelList.get(index).getId(), userRepository.getUserId());
                productModelList.remove(index);
                notifyItemRemoved(index);

                notificationUtil.showToast("Product removed from wishlist", true);
            });

            imgViewProductListImage = itemView.findViewById(R.id.imgViewProductListImage);

            itemView.setOnClickListener(this);
        }

        // Handle onClick events for the whole item view
        @Override
        public void onClick(View v) {
            int index = getAdapterPosition();
        }
    }
}
