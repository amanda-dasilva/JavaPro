package com.example.product.menu.controllers;

import com.example.product.menu.entity.Product;
import com.example.product.menu.service.ProductService;
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
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    @Test
    public void createProduct_Success() throws Exception {
        Product product = new Product(1L, "Cellphone1", 699.0, 13, 0.05);

        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/menu/products")
                        .content(asJsonString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getProduct_ShouldExistsInDb () throws Exception {
        mockMvc.perform(get("/api/menu/products"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void updateProduct_Success() throws Exception {
        Product updatedProduct = new Product(1L, "Cellphone", 3999.0, 15, 0.05);
        when(productService.create(any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(post("/api/menu/products")
                        .content(asJsonString(updatedProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(put("/api/menu/products/{id}", updatedProduct.getId())
                        .content(asJsonString(updatedProduct))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void deleteProduct_Success() throws Exception {
        Product productTest = new Product(1L, "Cellphone", 3999.0, 15, 0.05);
        when(productService.create(any(Product.class))).thenReturn(productTest);

        mockMvc.perform(post("/api/menu/products")
                        .content(asJsonString(productTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(delete("/api/menu/products/{id}", productTest.getId())
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
