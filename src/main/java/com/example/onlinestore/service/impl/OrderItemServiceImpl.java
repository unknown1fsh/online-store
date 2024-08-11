package com.example.onlinestore.service.impl;

import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.repository.OrderItemRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        // Stok kontrolü
        Product product = productRepository.findById(orderItem.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < orderItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product: " + product.getName());
        }

        // Stok güncellemesi
        product.setStock(product.getStock() - orderItem.getQuantity());
        productRepository.save(product);

        // OrderItem kaydet
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteById(Long id) {
        // OrderItem silinmeden önce stok geri eklenmeli
        orderItemRepository.findById(id).ifPresent(orderItem -> {
            Product product = orderItem.getProduct();
            product.setStock(product.getStock() + orderItem.getQuantity());
            productRepository.save(product);
            orderItemRepository.deleteById(id);
        });
    }
}
