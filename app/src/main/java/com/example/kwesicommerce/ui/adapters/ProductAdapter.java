package com.example.kwesicommerce.ui.adapters;

import static com.example.kwesicommerce.ui.adapters.CategoryRecyclerViewAdapter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.ui.activities.ProductDetailsActivity;
import com.example.kwesicommerce.utils.NotificationUtil;

import java.util.List;

public class ProductAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<ProductModel> productModelList;

    NotificationUtil notificationUtil;

    public ProductAdapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
        intent.putExtra("productId", productModelList.get(position).getId());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        v.getContext().startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    @Override
    public int getCount() {
        return productModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return productModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.layout_product_list, null);
        }

        TextView productTitle = convertView.findViewById(R.id.txtViewProductListTitle);
        TextView productPrice = convertView.findViewById(R.id.txtViewProductPrice);
        ImageView imgBtnFavorite = convertView.findViewById(R.id.imgBtnFavorite);
        ImageView imgViewProductListImage = convertView.findViewById(R.id.imgViewProductListImage);

        notificationUtil = new NotificationUtil(convertView.getContext());

        imgBtnFavorite.setOnClickListener(v -> {
            imgBtnFavorite.setColorFilter(Color.RED);

            Handler handler = new Handler();
            handler.postDelayed(() -> imgBtnFavorite.setColorFilter(Color.BLACK), 2000);

            // check if user is logged in
            UserRepository userRepository = new UserRepository(v.getContext());
            if (userRepository.isUserLoggedIn()) {
                int userId = userRepository.getUserId();
                WishlistRepository wishlistRepository = new WishlistRepository(v.getContext());

                if (wishlistRepository.addToWishlist(productModelList.get(position).getId(), userId) > 0) {
                    notificationUtil.showToast("Added to Wishlist", true);
                } else {
                    notificationUtil.showToast("Product already in Wishlist", false);
                }
            } else {
                notificationUtil.showToast("Please login to add to Wishlist", false);
            }
        });

        productTitle.setText(productModelList.get(position).getName());
        productPrice.setText(String.format("£%.2f", productModelList.get(position).getPrice()));

        Glide.with(convertView)
                .load(productModelList.get(position).getImageUrl())
                .override(150, 150)
                .centerCrop()
                .into(imgViewProductListImage);

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        return convertView;

    }
}