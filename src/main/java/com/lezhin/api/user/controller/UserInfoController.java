package com.lezhin.api.user.controller;

import com.lezhin.api.user.model.UserInfoResponse;
import com.lezhin.api.user.service.UserService;
import com.lezhin.common.Exception.ApiException;
import com.lezhin.common.Exception.CommonErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="유저 정보", description="유저 정보 API")
@Slf4j
@RequestMapping("/v1/user/*")
@RequiredArgsConstructor
@RestController
public class UserInfoController {
    private final UserService userService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping("/{userSeq}")
    public UserInfoResponse getUser(@Parameter(description = "userSeq", required = true) @PathVariable(value="userSeq") long userSeq) {
        if(userSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }
        UserInfoResponse info = userService.getUserinfo(userSeq);
        return userService.getUserinfo(userSeq);
    }

    @Operation(summary = "유저 정보 삭제")
    @DeleteMapping("/{userSeq}")
    public Boolean removeUser(@Parameter(description = "userSeq", required = true) @PathVariable(value="userSeq") long userSeq) {
        if(userSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }
        userService.removeUser(userSeq);
        return true;
    }

    @Operation(summary = "최근 1주일간 성인 작품을 3개이상 조회한 유저 리스트 조회")
    @GetMapping("/adult/content")
    public List<UserInfoResponse> getAdultContentSearchUser(@Parameter(description = "days", required = true) @RequestParam(value="days" , defaultValue="7") int days) {
        if(days > 30){
            throw new ApiException(CommonErrorCode.COMMON_BAD_REQUEST);
        }
        List<UserInfoResponse> responseList = userService.getAdultSearchUserList(days);
        return responseList;
    }


}
