package com.mentorama.productapi.controllers;

import com.mentorama.productapi.models.Product;
import com.mentorama.productapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    public List<Product> findAll() {
        return productService.findAll();
    }

}
