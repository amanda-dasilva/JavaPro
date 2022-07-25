package com.example.product.menu.repository;

import com.example.product.menu.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderItem, Long> {
}
