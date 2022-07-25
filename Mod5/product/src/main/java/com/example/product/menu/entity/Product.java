package com.example.product.menu.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private String name;
    @NotNull(message = "price is required")
    @Positive(message = "price must be positive")
    private Double price;
    @NotNull(message = "quantity is required")
    @Positive(message = "quantity must be positive")
    private Integer quantity;
    @NotNull
    private Double maxDiscount;


    public Product() {
        super();
        this.id = null;
        this.name = null;
        this.price = null;
        this.quantity = null;
        this.maxDiscount = null;
    }
    public Product(Long id, String name, Double price, Integer quantity, Double maxDiscount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.maxDiscount = maxDiscount;
    }

    public Double getPriceWithDiscount(final Double discount) {
        if(discount > maxDiscount) {
            return price * (1 - maxDiscount);
        }
        else {
            return price * (1 - discount);
        }
    }

    public Integer getQtyOfProductStock(final Integer qtyProduct) {
        if(qtyProduct > quantity) {
            return quantity;
        } else {
            return qtyProduct;
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Product updateWith(Product product) {
        return new Product(
                this.id,
                product.name,
                product.price,
                product.quantity,
                product.maxDiscount
        );
    }
}
