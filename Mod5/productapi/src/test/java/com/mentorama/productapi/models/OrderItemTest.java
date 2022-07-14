package com.mentorama.productapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    public void shouldCalculateTotalPrice() {
        final Product product = new Product(1L, "Coca-Cola", 10.0, 5.0);
        final OrderItem orderItem = new OrderItem(product, 3, 0.0);
        final Double result = orderItem.totalPrice();
        assertEquals(30.00, result);
    }

}