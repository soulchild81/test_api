package com.lezhin.api.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class UserInfoResponse {
    private long userSeq;
    private String name;
    private String email;
    private UserGender gender;
    private UserType type;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer searchCnt;
}
