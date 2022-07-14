package com.mentorama.productapi.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void shouldCalculateTotalPriceWithDiscount() {
        Product product = new Product(1l, "TEST", 100.0, 0.10);
        Double result = product.getPriceWithDiscount(0.10);
        assertEquals(90.00, result);
    }

    @Test
    public void whenDiscountIsHigherThenMaxDiscountShouldUseMaxDiscountPercentage() {
        Product product = new Product(1l, "TEST", 100.0, 0.10);
        Double result = product.getPriceWithDiscount(0.15);
        assertEquals(90.00, result);
    }

    @Test
    public void whenDiscountIsLessThenMaxDiscountShouldUseMaxDiscountPercentage() {
        Product product = new Product(1l, "TEST", 100.0, 0.10);
        Double result = product.getPriceWithDiscount(0.05);
        assertEquals(95.00, result);
    }
}
