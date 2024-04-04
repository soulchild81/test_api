package com.lezhin.common.Exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ErrorResponse> handleException(ApiException ex) {
        return ErrorResponse.toResponseEntity(ex.getErrorCode(), ex.getMessage());
    }
}
