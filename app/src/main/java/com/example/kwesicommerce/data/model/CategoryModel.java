package com.example.kwesicommerce.data.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private int id;
    private String name;
    private List<ProductModel> productModels;

    public CategoryModel() {
        this.name = "";
        this.productModels = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(ProductModel productModel) {
        this.productModels.add(productModel);
    }

    public void removeProduct(ProductModel productModel) {
        this.productModels.remove(productModel);
    }

    public List<ProductModel> getProducts() {
        return this.productModels;
    }

    public void setProducts(List<ProductModel> productModels) {
        this.productModels = productModels;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
