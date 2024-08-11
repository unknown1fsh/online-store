package com.example.onlinestore.repository;

import com.example.onlinestore.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> { // Arayüz adı OrderRepository yerine CustomerOrderRepository olarak değiştirildi
}
