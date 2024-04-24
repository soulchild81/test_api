package com.lezhin.api.content.mapper;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.entity.Content;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T15:16:52+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class ContentMapperImpl implements ContentMapper {

    @Override
    public ContentResponse toContentResponse(Content content) {
        if ( content == null ) {
            return null;
        }

        ContentResponse.ContentResponseBuilder contentResponse = ContentResponse.builder();

        contentResponse.contentSeq( content.getContentSeq() );
        contentResponse.contentsName( content.getContentsName() );
        contentResponse.author( content.getAuthor() );
        contentResponse.coin( content.getCoin() );
        contentResponse.openDate( content.getOpenDate() );
        contentResponse.likeCount( content.getLikeCount() );
        contentResponse.disLikeCount( content.getDisLikeCount() );

        return contentResponse.build();
    }
}
