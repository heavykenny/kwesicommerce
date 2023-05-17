package com.example.kwesicommerce.data.model;

public class CartItemModel {
    private final ProductModel productModel;
    private int quantity;
    private int id;

    public CartItemModel(int id, ProductModel productModel, int quantity) {
        this.id = id;
        this.productModel = productModel;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return productModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

