package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CustomerDTO;
import com.example.onlinestore.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}
