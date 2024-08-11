package com.example.onlinestore.service.impl;

import com.example.onlinestore.dto.CustomerOrderDTO;
import com.example.onlinestore.dto.OrderItemDTO;
import com.example.onlinestore.entity.Customer;
import com.example.onlinestore.entity.CustomerOrder;
import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.CustomerOrderMapper;
import com.example.onlinestore.mapper.OrderItemMapper;
import com.example.onlinestore.repository.CustomerOrderRepository;
import com.example.onlinestore.repository.CustomerRepository;
import com.example.onlinestore.repository.OrderItemRepository;
import com.example.onlinestore.repository.ProductRepository;
import com.example.onlinestore.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private CustomerOrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<CustomerOrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerOrderDTO getOrderById(Long id) {
        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional
    public CustomerOrderDTO createOrder(CustomerOrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Siparişi oluştur ve müşteriyi ekle
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setOrderDate(orderDTO.getOrderDate()); // Sipariş tarihini ayarlıyoruz

        double total = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        // Her bir OrderItemDTO için işleme başla
        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItems()) {
            Product product = productRepository.findById(orderItemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < orderItemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // OrderItem oluştur ve gerekli ilişkileri kur
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); // CustomerOrder ilişkisini kuruyoruz
            orderItem.setProduct(product); // Product ilişkisini kuruyoruz
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());

            // Stok güncelleme
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);

            total += orderItem.getQuantity() * orderItem.getPrice();
            orderItems.add(orderItem); // OrderItem'ları listeye ekliyoruz
        }

        order.setOrderItems(orderItems); // Listeyi siparişe ekliyoruz
        order.setTotal(total);
        order = orderRepository.save(order); // Siparişi ve bağlı OrderItem'ları kaydediyoruz

        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem); // Her bir orderItem'i ayrı olarak kaydediyoruz
        }

        // DTO'ya dönüştür ve döndür
        return orderMapper.toDTO(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
