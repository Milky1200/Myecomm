package com.mishraaditya.mycomm.ServerClients;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    List<ProductModel> products;

    public ProductResponse(List<ProductModel> products) {
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
