package com.mentorama.productapi.models;

import java.util.List;

public class ProductDTO {

    private List<Product> products;

    public ProductDTO(List<Product> products) {
        this.products = products;
    }

    public ProductDTO() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
