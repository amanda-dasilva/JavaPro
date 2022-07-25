package com.example.product.menu.service;

import com.example.product.menu.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@EnableMapRepositories
public class ProductService {

    private final CrudRepository<Product, Long> productRepository;

    public ProductService(CrudRepository<Product, Long> productRepository) {
        this.productRepository = productRepository;
        this.productRepository.saveAll(defaultProducts());
    }

    private static List<Product> defaultProducts() {
        return List.of(
                new Product(1L, "Cellphone1", 899.0, 15, 0.05),
                new Product(2L, "Cellphone2", 999.0, 15, 0.10),
                new Product(3L, "Cellphone3", 799.0, 15, 0.15)
        );
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Iterable<Product> products = productRepository.findAll();
        products.forEach(productList::add);
        return productList;
    }

    public Optional<Product> find(Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Product copy = new Product(
                new Date().getTime(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getMaxDiscount()
        );
        return productRepository.save(copy);
    }

    public Optional<Product> update(Long id, Product newProduct) {
        // Only update an item if it can be found first.
        return productRepository.findById(id)
                .map(oldProduct -> {
                    Product updated = oldProduct.updateWith(newProduct);
                    return productRepository.save(updated);
                });
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
