package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CustomerOrderDTO;
import com.example.onlinestore.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface CustomerOrderMapper {

    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);

    @Mappings({
        @Mapping(source = "customer.id", target = "customerId"),
        // `orderItems` listesini otomatik olarak OrderItemMapper ile dönüştürür
    })
    CustomerOrderDTO toDTO(CustomerOrder order);

    @Mappings({
        @Mapping(source = "customerId", target = "customer.id"),
        // `orderItems` listesini otomatik olarak OrderItemMapper ile dönüştürür
    })
    CustomerOrder toEntity(CustomerOrderDTO orderDTO);
}
