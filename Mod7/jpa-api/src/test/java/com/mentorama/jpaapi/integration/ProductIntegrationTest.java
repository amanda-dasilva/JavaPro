package com.mentorama.jpaapi.integration;

import com.mentorama.jpaapi.controller.ProductController;
import com.mentorama.jpaapi.entity.ProductEntity;
import com.mentorama.jpaapi.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void getProduct_ShouldFindAll() throws Exception {
        productRepository.save(aProductEntity("Description 1"));
        productRepository.save(aProductEntity("Description 2"));

        Iterable<ProductEntity> products = productController.findAll();
        Assertions.assertThat(products).hasSize(2);

    }

    @Test
    public void getProduct_ShouldFindByDescription() throws Exception {
        productRepository.save(aProductEntity("Description 1"));
        productRepository.save(aProductEntity("Description 2"));
        productRepository.save(aProductEntity("Qualquer coisa"));

        List<ProductEntity> productEntities = productController.findByDescription("Descrip");

        Assertions.assertThat(productEntities).hasSize(2);

    }

    private ProductEntity aProductEntity(String description) {
        final ProductEntity product = new ProductEntity();
        product.setDescription(description);
        product.setValue(10.0);
        return product;
    }
}
