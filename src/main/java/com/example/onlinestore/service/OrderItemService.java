package com.example.onlinestore.service;

import com.example.onlinestore.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> findAll();

    Optional<OrderItem> findById(Long id);

    OrderItem save(OrderItem orderItem);

    void deleteById(Long id);
}
