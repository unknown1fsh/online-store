package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.ProductDTO;
import com.example.onlinestore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDTO(Product product);
    
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDTO productDTO);
}
