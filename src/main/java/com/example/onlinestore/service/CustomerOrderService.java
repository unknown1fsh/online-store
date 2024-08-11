package com.example.onlinestore.service;

import com.example.onlinestore.dto.CustomerOrderDTO;
import java.util.List;

public interface CustomerOrderService { // Arayüz adı OrderService yerine CustomerOrderService olarak değiştirildi
    List<CustomerOrderDTO> getAllOrders();
    CustomerOrderDTO getOrderById(Long id);
    CustomerOrderDTO createOrder(CustomerOrderDTO orderDTO);
    void deleteOrder(Long id);
}
