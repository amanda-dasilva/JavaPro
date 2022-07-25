package com.example.product.menu.controllers;

import com.example.product.menu.entity.OrderItem;
import com.example.product.menu.entity.Product;
import com.example.product.menu.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("api/menu/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderItem> createAnOrder(@RequestBody OrderItem orderItem) throws RuntimeException {
        orderService.createOrder(orderItem);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderItem.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(orderItem);
    }

    @GetMapping
    public ResponseEntity<Double> getTotalOrder(@RequestBody OrderItem orderItem) throws RuntimeException {
        return ResponseEntity.ok().body(orderService.calculateOrder(orderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable("id") Long id,
            @RequestBody OrderItem updatedOrderItem) throws RuntimeException {

        Optional<OrderItem> updated = orderService.updateOrderItem(id, updatedOrderItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    orderService.createOrder(updatedOrderItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(updatedOrderItem.getOrderId())
                            .toUri();
                    return ResponseEntity.created(location).body(updatedOrderItem);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteOrderItem(@PathVariable("id") Long id) throws RuntimeException {
        orderService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
