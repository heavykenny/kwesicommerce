package com.example.kwesicommerce.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kwesicommerce.R;
import com.example.kwesicommerce.data.model.Category;
import com.example.kwesicommerce.data.model.Product;
import com.example.kwesicommerce.ui.adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private ProductAdapter adapter;
    private List<Product> productList;
    private GridView quizView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        quizView = view.findViewById(R.id.gridViewProduct);


        // Create a list of Product objects
        productList = new ArrayList<>();
        Category cat = new Category("Gadgets");
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        productList.add(new Product("Flower", "Lorem Text", "R.drawable.image_placeholder", 100, 100, 100, cat));
        // Create a new ProductAdapter with the list of products
        adapter = new ProductAdapter(productList);

//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        quizView.setLayoutManager(layoutManager);

        quizView.setAdapter(adapter);

        return view;
    }
}
