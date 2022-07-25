package com.example.product.menu.controllers;

import com.example.product.menu.entity.OrderItem;
import com.example.product.menu.entity.Product;
import com.example.product.menu.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;


    @Test
    public void createOrderItem_Success() throws Exception {
        Product product = new Product(1L, "Cellphone1", 699.0, 13, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        mockMvc.perform(post("/api/menu/order")
                        .content(asJsonString(orderItem))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void calcTotalOrder_Success() throws Exception {
        Product product = new Product(1L, "Cellphone1", 699.0, 13, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        when(orderService.calculateOrder(any(OrderItem.class))).thenReturn(orderItem.totalPrice());

        mockMvc.perform(get("/api/menu/order")
                        .content(asJsonString(orderItem))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateOrderItem_Success() throws Exception {
        Product product = new Product(1L, "Cellphone1", 699.0, 13, 0.05);
        OrderItem updatedOrderItem = new OrderItem(1L, product, 7, 0.15);

        mockMvc.perform(put("/api/menu/order/{id}", updatedOrderItem.getOrderId())
                        .content(asJsonString(updatedOrderItem))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteOrderItem_Success() throws Exception {
        Product product = new Product(1L, "Cellphone1", 699.0, 13, 0.05);
        OrderItem orderItem = new OrderItem(1L, product, 7, 0.15);

        mockMvc.perform(delete("/api/menu/order/{id}", orderItem.getOrderId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
