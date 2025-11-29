package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CustomerOrderDTO;
import com.example.onlinestore.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface CustomerOrderMapper {

    @Mappings({
        @Mapping(source = "customer.id", target = "customerId"),
    })
    CustomerOrderDTO toDTO(CustomerOrder order);

    @Mappings({
        @Mapping(source = "customerId", target = "customer.id"),
        @Mapping(target = "orderItems", ignore = true)
    })
    CustomerOrder toEntity(CustomerOrderDTO orderDTO);
}
