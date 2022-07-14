package com.mentorama.productapi.services;

import com.mentorama.productapi.models.OrderItem;
import com.mentorama.productapi.models.Order;

import java.util.List;

public class OrderCalculator {

    public Double calculateOrder(final Order order) {
        return order.getItems().stream().mapToDouble(OrderItem::totalPrice)
                .sum();
    }

    public Double calculateMultipleOrders(final List<Order> orders) {
        return orders.stream()
                .mapToDouble(order -> calculateOrder(order))
                .sum();
    }
}
