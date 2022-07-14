package com.mentorama.productapi.clients;

import com.mentorama.productapi.models.Product;
import com.mentorama.productapi.models.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClientAPI {

    @Value("${product.api.url}")
    private String url;

    public List<Product> findAll() {
        ResponseEntity<ProductDTO> responseEntity =
                new RestTemplate().getForEntity(url, ProductDTO.class);
        return responseEntity.getBody().getProducts();
    }
}
