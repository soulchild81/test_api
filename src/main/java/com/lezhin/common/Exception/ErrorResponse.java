package com.lezhin.common.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lezhin.common.response.ApiBaseResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
@Getter
@Builder
public class ErrorResponse implements ApiBaseResponse {

    private final String code;
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private final ZonedDateTime timestamp;

    private final int status;
    private final String error;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode, String overridedMessage) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(overridedMessage)
                        .timestamp(ZonedDateTime.now())
                        .status(errorCode.getStatus().value())
                        .error(errorCode.getStatus().name())
                        .build()
                );
    }

}
