package com.lezhin.common.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

@RestControllerAdvice
public class ApiResponseHandlerAdvice implements ResponseBodyAdvice<Object> {

    private static final List<String> OPEN_API_DOCS_URI_PATTERNS = List.of("/v3/api-docs**", "/api-docs**");

    private static final Predicate<MethodParameter> isRestController = mp -> mp.getContainingClass().isAnnotationPresent(RestController.class);
    private static final Predicate<MethodParameter> hasIgnoreApiResponseBiding = mp ->
            mp.getContainingClass().isAnnotationPresent(IgnoreApiResponse.class) ||
                    mp.hasMethodAnnotation(IgnoreApiResponse.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        if (not(isRestController).or(hasIgnoreApiResponseBiding).test(returnType)) {
            return body;
        }

        if (OPEN_API_DOCS_URI_PATTERNS.stream().anyMatch(pattern -> PatternMatchUtils.simpleMatch(pattern, request.getURI().getPath()))) {
            return body;
        }

        return ApiSuccessResponse.of(body);
    }
}
