package com.mentorama.productapi.models;

import java.util.List;

public class Order {

    private final List<OrderItem> items;

    public Order(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
