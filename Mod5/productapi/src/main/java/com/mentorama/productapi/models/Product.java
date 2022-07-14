package com.mentorama.productapi.models;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Double maxDiscountPercentage;

    public Product(Long id, String name, Double price, Double maxDiscountPercentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maxDiscountPercentage = maxDiscountPercentage;
    }

    public Double getPriceWithDiscount(final Double discount) {
        if(discount > maxDiscountPercentage) {
            return price * (1-maxDiscountPercentage);
        }
        else {
            return price * (1 - discount);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMaxDiscountPercentage() {
        return maxDiscountPercentage;
    }

    public void setMaxDiscountPercentage(Double maxDiscountPercentage) {
        this.maxDiscountPercentage = maxDiscountPercentage;
    }
}
