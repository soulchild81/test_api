package com.lezhin.common.Exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiException extends RuntimeException{
    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
