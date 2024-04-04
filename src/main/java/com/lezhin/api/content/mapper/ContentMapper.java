package com.lezhin.api.content.mapper;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    ContentResponse toContentResponse(Content content);
}
