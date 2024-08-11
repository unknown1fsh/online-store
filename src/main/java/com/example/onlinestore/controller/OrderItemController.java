package com.example.onlinestore.controller;

import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.save(orderItem);
        return ResponseEntity.ok(savedOrderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        return orderItemService.findById(id)
                .map(orderItem -> {
                    orderItem.setProduct(orderItemDetails.getProduct());
                    orderItem.setOrder(orderItemDetails.getOrder());
                    orderItem.setQuantity(orderItemDetails.getQuantity());
                    orderItem.setPrice(orderItemDetails.getPrice());
                    OrderItem updatedOrderItem = orderItemService.save(orderItem);
                    return ResponseEntity.ok(updatedOrderItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
