package com.lezhin.api.user.mapper;

import com.lezhin.api.user.model.AdultContentSearchUser;
import com.lezhin.api.user.model.ContentViewerResponse;
import com.lezhin.api.user.model.UserInfoResponse;
import com.lezhin.entity.ContentViewer;
import com.lezhin.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target="gender" , expression="java(com.lezhin.api.user.model.UserGender.of(userInfo.getGender()))")
    @Mapping(target="name"   , source="userName")
    @Mapping(target="email"  , source="userEmail")
    UserInfoResponse toUserInfoResponse(UserInfo userInfo);

    @Mapping(target="gender" , expression="java(com.lezhin.api.user.model.UserGender.of(user.getGender()))")
    @Mapping(target="name"   , source="userName")
    @Mapping(target="email"  , source="userEmail")
    UserInfoResponse toUserInfoResponse(AdultContentSearchUser user);

    @Mapping(target="contentSeq" , source="viewerKey.contentSeq")
    @Mapping(target="userSeq"    , source="viewerKey.userSeq")
    ContentViewerResponse toContentViewerResponse(ContentViewer viewer);
}
