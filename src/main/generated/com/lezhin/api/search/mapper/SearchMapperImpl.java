package com.lezhin.api.search.mapper;

import com.lezhin.api.search.model.Category;
import com.lezhin.api.search.model.CategoryResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T15:40:17+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class SearchMapperImpl implements SearchMapper {

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setUpper_category_id( category.getUpper_display_category_id() );
        categoryResponse.setDisplay_category_id( category.getDisplay_category_id() );
        categoryResponse.setDisplay_category_name( category.getDisplay_category_name() );
        categoryResponse.setIcon_img_url( category.getIcon_img_url() );

        return categoryResponse;
    }
}
