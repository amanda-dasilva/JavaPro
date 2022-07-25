package com.example.product.menu.service;

import com.example.product.menu.entity.Order;
import com.example.product.menu.entity.OrderItem;
import com.example.product.menu.entity.Product;
import com.example.product.menu.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @MockBean
    OrderService orderService;

    @BeforeEach
    void initUseCase() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void getOrder_ShouldCalculateTotalPriceWithDiscount() {
        final Product product = new Product(1L, "Cellphone", 499.9, 5, 0.05);
        final OrderItem orderItem = new OrderItem(1L, product, 3, 0.10);
        final Double result = orderItem.totalPrice();
        assertEquals(1424.715, result);
    }

    @Test
    public void getOrder_ShouldValidateStockOfProduct() {
        final Product product = new Product(1L, "Cellphone", 499.9, 5, 0.05);
        final OrderItem orderItem = new OrderItem(1L, product, 7, 0.05);
        final Integer result = orderItem.qtyProduct();
        assertEquals(5, result);
    }

    @Test
    public void getOrder_ShouldCalculateTotalPriceOfOrderItem() {
        final Product product = new Product(1L, "Cellphone", 499.0, 5, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        final Double totalOrder = orderService.calculateOrder(orderItem);
        assertEquals(2370.25, totalOrder);
    }

    @Test
    public void getOrder_ShouldCreateAnOrderItem() {
        final Product product = new Product(1L, "Cellphone", 499.0, 5, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);
        List<OrderItem> orderList = Arrays.asList(orderItem);
        Order order = new Order(orderList);

        orderService.createOrder(orderItem);

        assertTrue(order.getItems() == orderList);
    }

    @Test
    public void updatedOrderItem_Success() {
        final Product product = new Product(1L, "Cellphone", 499.0, 5, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        orderService.updateOrderItem(orderItem.getOrderId(), orderItem);

        verify(orderRepository).findById(orderItem.getOrderId());
    }

    @Test
    public void deletedOrderItem_Success() {
        final Product product = new Product(1L, "Cellphone", 499.0, 5, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        orderService.deleteOrderItem(orderItem.getOrderId());
        assertFalse(orderItem.getOrderId() == null);
    }

}
