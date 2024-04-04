package com.lezhin.common.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    COMMON_ILLEGAL_PARAM(NOT_FOUND, "지원하지 않는 파라미터 형식입니다."),
    COMMON_NOT_FOUND(NOT_FOUND, "데이터가 존재하지 않습니다."),
    COMMON_BAD_REQUEST(BAD_REQUEST, "잘못된 호출입니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
