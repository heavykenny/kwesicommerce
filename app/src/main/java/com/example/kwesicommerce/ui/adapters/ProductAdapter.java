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
import android.widget.Toast;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.data.repository.UserRepository;
import com.example.kwesicommerce.data.repository.WishlistRepository;
import com.example.kwesicommerce.ui.activities.ProductDetailsActivity;

import java.util.List;

public class ProductAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<ProductModel> productModelList;

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
                    Toast.makeText(v.getContext(), "Added to Wishlist ---" + wishlistRepository.addToWishlist(productModelList.get(position).getId(), userId), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Failed to add to Wishlist == "+ wishlistRepository.addToWishlist(productModelList.get(position).getId(), userId), Toast.LENGTH_SHORT).show();
                }

                // create toast message
//                Toast.makeText(v.getContext(), "Added to Wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Please login to add to wishlist", Toast.LENGTH_SHORT).show();
            }
        });

        productTitle.setText(productModelList.get(position).getName());
        productPrice.setText(String.format("£%.2f", productModelList.get(position).getPrice()));

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        return convertView;

    }
}