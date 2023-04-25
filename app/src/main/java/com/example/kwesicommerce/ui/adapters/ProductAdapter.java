package com.example.kwesicommerce.ui.adapters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Toast.makeText(v.getContext(), productList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.product_layout, null);
        }

//        ImageView productImage = convertView.findViewById(R.id.product_image);
        TextView productTitle = convertView.findViewById(R.id.product_title);
        TextView productDescription = convertView.findViewById(R.id.product_description);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        ImageButton productWishlist = convertView.findViewById(R.id.product_wishlist);

        productTitle.setText(productList.get(position).getName());
        productDescription.setText(productList.get(position).getDescription());
        productPrice.setText(String.format("£%.2f", productList.get(position).getPrice()));
//        productImage.setImageURI(Uri.parse(productList.get(position).getImageUrl()));

        convertView.setTag(position);
        convertView.setOnClickListener(this);

        return convertView;

    }
}