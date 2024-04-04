package com.lezhin.api.user.mapper;

import com.lezhin.api.user.model.AdultContentSearchUser;
import com.lezhin.api.user.model.ContentViewerResponse;
import com.lezhin.api.user.model.UserInfoResponse;
import com.lezhin.api.user.model.UserType;
import com.lezhin.entity.ContentViewer;
import com.lezhin.entity.UserInfo;
import com.lezhin.entity.entityKey.ContentUserKey;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T11:02:52+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserInfoResponse toUserInfoResponse(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserInfoResponse.UserInfoResponseBuilder userInfoResponse = UserInfoResponse.builder();

        userInfoResponse.name( userInfo.getUserName() );
        userInfoResponse.email( userInfo.getUserEmail() );
        if ( userInfo.getUserSeq() != null ) {
            userInfoResponse.userSeq( userInfo.getUserSeq() );
        }
        if ( userInfo.getType() != null ) {
            userInfoResponse.type( Enum.valueOf( UserType.class, userInfo.getType() ) );
        }

        userInfoResponse.gender( com.lezhin.api.user.model.UserGender.of(userInfo.getGender()) );

        return userInfoResponse.build();
    }

    @Override
    public UserInfoResponse toUserInfoResponse(AdultContentSearchUser user) {
        if ( user == null ) {
            return null;
        }

        UserInfoResponse.UserInfoResponseBuilder userInfoResponse = UserInfoResponse.builder();

        userInfoResponse.name( user.getUserName() );
        userInfoResponse.email( user.getUserEmail() );
        userInfoResponse.userSeq( user.getUserSeq() );
        if ( user.getType() != null ) {
            userInfoResponse.type( Enum.valueOf( UserType.class, user.getType() ) );
        }
        userInfoResponse.searchCnt( user.getSearchCnt() );

        userInfoResponse.gender( com.lezhin.api.user.model.UserGender.of(user.getGender()) );

        return userInfoResponse.build();
    }

    @Override
    public ContentViewerResponse toContentViewerResponse(ContentViewer viewer) {
        if ( viewer == null ) {
            return null;
        }

        ContentViewerResponse contentViewerResponse = new ContentViewerResponse();

        Long contentSeq = viewerViewerKeyContentSeq( viewer );
        if ( contentSeq != null ) {
            contentViewerResponse.setContentSeq( contentSeq );
        }
        Long userSeq = viewerViewerKeyUserSeq( viewer );
        if ( userSeq != null ) {
            contentViewerResponse.setUserSeq( userSeq );
        }
        contentViewerResponse.setCreatedDate( viewer.getCreatedDate() );
        contentViewerResponse.setModifiedDate( viewer.getModifiedDate() );

        return contentViewerResponse;
    }

    private Long viewerViewerKeyContentSeq(ContentViewer contentViewer) {
        if ( contentViewer == null ) {
            return null;
        }
        ContentUserKey viewerKey = contentViewer.getViewerKey();
        if ( viewerKey == null ) {
            return null;
        }
        Long contentSeq = viewerKey.getContentSeq();
        if ( contentSeq == null ) {
            return null;
        }
        return contentSeq;
    }

    private Long viewerViewerKeyUserSeq(ContentViewer contentViewer) {
        if ( contentViewer == null ) {
            return null;
        }
        ContentUserKey viewerKey = contentViewer.getViewerKey();
        if ( viewerKey == null ) {
            return null;
        }
        Long userSeq = viewerKey.getUserSeq();
        if ( userSeq == null ) {
            return null;
        }
        return userSeq;
    }
}
