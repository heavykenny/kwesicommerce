package com.example.kwesicommerce.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.ProductModel;
import com.example.kwesicommerce.ui.adapters.ProductAdapter;

import java.util.List;

public class ProductFragment extends Fragment {

    private final List<ProductModel> productModelList;

    public ProductFragment(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        GridView productView = view.findViewById(R.id.gridViewProduct);

        // set adapter for product view
        ProductAdapter adapter = new ProductAdapter(productModelList);
        productView.setAdapter(adapter);
        return view;
    }
}
