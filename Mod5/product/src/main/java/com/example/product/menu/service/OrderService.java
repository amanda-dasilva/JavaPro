package com.example.product.menu.service;

import com.example.product.menu.entity.Order;
import com.example.product.menu.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final CrudRepository<OrderItem, Long> orderRepository;

    public OrderService(CrudRepository<OrderItem, Long> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(final OrderItem orderItem) {
        orderRepository.save(orderItem);
    }

    public Optional<OrderItem> updateOrderItem(Long id, OrderItem newOrderItem) {
        return orderRepository.findById(id)
                .map(oldOrder -> {
                    OrderItem updated = oldOrder.updateWith(newOrderItem);
                    return orderRepository.save(updated);
                });
    }
    public Double calculateOrder(OrderItem orderItem) {
        List<OrderItem> orderItemList = Arrays.asList(orderItem);
        Order order = new Order(orderItemList);

        return order.getItems().stream().mapToDouble(OrderItem::totalPrice)
                .sum();
    }

    public void deleteOrderItem(Long id) {
        orderRepository.deleteById(id);
    }
}
