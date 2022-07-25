package com.example.product.menu.service;

import com.example.product.menu.entity.Product;
import com.example.product.menu.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @MockBean
    ProductService productService;

    @BeforeEach
    void initUseCase() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void createdProduct_Success() {
        Product product = new Product();
        product.setId(5L);
        product.setName("Cellphone5");
        product.setPrice(499.0);
        product.setQuantity(4);
        product.setMaxDiscount(0.10);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getName()).isNotNull();
    }

    @Test
    public void getProduct_ShouldExistsInDb() {
        Product product = new Product(1L, "Cellphone", 3999.0, 15, 0.05);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> fetchProducts = productService.findAll();
        assertThat(fetchProducts.size()).isGreaterThan(0);
    }

    @Test
    public void updatedProduct_Success() {
        Product productTest = new Product(1L, "Cellphone", 3999.0, 15, 0.05);

        //providing knowledge
        when(productRepository.save(any(Product.class))).thenReturn(productTest);

        Product updatedProduct = productRepository.save(productTest);

        productService.update(productTest.getId(), updatedProduct);

        verify(productRepository).save(updatedProduct);
        verify(productRepository).findById(productTest.getId());
    }

    @Test
    public void deletedProduct_Success() {
        Product productTest = new Product(1L, "Cellphone", 3999.0, 15, 0.05);

        productService.delete(productTest.getId());
        verify(productRepository).deleteById(productTest.getId());
    }

}
