package com.example.onlinestore.service;

import com.example.onlinestore.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();

    OrderItemDTO findById(Long id);

    OrderItemDTO save(OrderItemDTO orderItemDTO);

    OrderItemDTO update(Long id, OrderItemDTO orderItemDTO);

    void deleteById(Long id);
}
