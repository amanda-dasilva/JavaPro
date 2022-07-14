package com.mentorama.productapi.services;

import com.mentorama.productapi.clients.ProductClientAPI;
import com.mentorama.productapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductClientAPI client;

    public List<Product> findAll() {
        return client.findAll();
    }
}
