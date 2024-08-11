package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CategoryDTO;
import com.example.onlinestore.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}
