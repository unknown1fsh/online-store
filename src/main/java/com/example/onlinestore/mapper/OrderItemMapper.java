package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.OrderItemDTO;
import com.example.onlinestore.entity.CustomerOrder;
import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.repository.CustomerOrderRepository;

@Mapper(componentModel = "spring")
public abstract class OrderItemMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    public abstract OrderItemDTO toDTO(OrderItem orderItem);

    @Mapping(source = "productId", target = "product", qualifiedByName = "productFromId")
    @Mapping(source = "orderId", target = "order", qualifiedByName = "orderFromId")
    public abstract OrderItem toEntity(OrderItemDTO orderItemDTO);

    @Named("productFromId")
    protected Product productFromId(Long productId) {
        if (productId == null) {
            return null;
        }
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Named("orderFromId")
    protected CustomerOrder orderFromId(Long orderId) {
        if (orderId == null) {
            return null;
        }
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
