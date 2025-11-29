package com.example.onlinestore.service.impl;

import com.example.onlinestore.dto.OrderItemDTO;
import com.example.onlinestore.entity.CustomerOrder;
import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.exception.ResourceNotFoundException;
import com.example.onlinestore.mapper.OrderItemMapper;
import com.example.onlinestore.repository.CustomerOrderRepository;
import com.example.onlinestore.repository.OrderItemRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final CustomerOrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, 
                                 ProductRepository productRepository,
                                 CustomerOrderRepository orderRepository,
                                 OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    public List<OrderItemDTO> findAll() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO findById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        return orderItemMapper.toDTO(orderItem);
    }

    @Override
    @Transactional
    public OrderItemDTO save(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getProductId() == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemDTO.getProductId()));

        if (product.getStock() < orderItemDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName() + ". Available: " + product.getStock() + ", Requested: " + orderItemDTO.getQuantity());
        }

        // OrderItem oluştur
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDTO);
        
        // Stok güncellemesi
        product.setStock(product.getStock() - orderItem.getQuantity());
        productRepository.save(product);

        // OrderItem kaydet
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDTO(savedOrderItem);
    }

    @Override
    @Transactional
    public OrderItemDTO update(Long id, OrderItemDTO orderItemDTO) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        
        // Eski stoku geri ekle
        Product oldProduct = existingOrderItem.getProduct();
        oldProduct.setStock(oldProduct.getStock() + existingOrderItem.getQuantity());
        productRepository.save(oldProduct);
        
        // Yeni ürün kontrolü
        if (orderItemDTO.getProductId() == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        
        Product newProduct = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderItemDTO.getProductId()));

        if (newProduct.getStock() < orderItemDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product: " + newProduct.getName() + ". Available: " + newProduct.getStock() + ", Requested: " + orderItemDTO.getQuantity());
        }

        // OrderItem güncelle
        existingOrderItem.setProduct(newProduct);
        existingOrderItem.setQuantity(orderItemDTO.getQuantity());
        existingOrderItem.setPrice(orderItemDTO.getPrice());
        if (orderItemDTO.getOrderId() != null) {
            CustomerOrder order = orderRepository.findById(orderItemDTO.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemDTO.getOrderId()));
            existingOrderItem.setOrder(order);
        }

        // Yeni stoku güncelle
        newProduct.setStock(newProduct.getStock() - orderItemDTO.getQuantity());
        productRepository.save(newProduct);

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return orderItemMapper.toDTO(updatedOrderItem);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        
        // Stok geri ekle
        Product product = orderItem.getProduct();
        product.setStock(product.getStock() + orderItem.getQuantity());
        productRepository.save(product);
        
        orderItemRepository.deleteById(id);
    }
}
