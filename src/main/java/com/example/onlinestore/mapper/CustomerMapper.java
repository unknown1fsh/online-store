package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CustomerDTO;
import com.example.onlinestore.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    
    @Mapping(target = "orders", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);
}
