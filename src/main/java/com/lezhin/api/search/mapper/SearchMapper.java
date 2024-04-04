package com.lezhin.api.search.mapper;

import com.lezhin.api.search.model.Category;
import com.lezhin.api.search.model.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchMapper {
    SearchMapper INSTANCE = Mappers.getMapper(SearchMapper.class);

    @Mapping(target="upper_category_id"   , source="upper_display_category_id")
    CategoryResponse toCategoryResponse(Category category);
}
