package com.lezhin.common.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiSuccessResponse<T> implements ApiBaseResponse {

    private String code;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime timestamp;

    private T data;

    public static <T> ApiSuccessResponse<T> of(T data) {
        return ApiSuccessResponse.<T>builder()
            .code("SUCCESS")
            .message("This response is successful")
            .data(data)
            .timestamp(ZonedDateTime.now())
            .build();
    }
}
