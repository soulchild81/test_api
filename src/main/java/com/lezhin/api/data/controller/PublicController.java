package com.lezhin.api.data.controller;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.api.data.feign.PublicClient;
import com.lezhin.api.data.result.XmlResult;
import com.lezhin.common.Exception.ApiException;
import com.lezhin.common.Exception.CommonErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name="공공 데이터 정보", description="공공 API")
@Slf4j
@RequestMapping("/v1/public/*")
@RequiredArgsConstructor
@RestController
public class PublicController {
    private final PublicClient publicClient;

    @Operation(summary = "실시간 지하철 도착 정보")
    @GetMapping("/subway/{station}")
    public XmlResult getSubway(@Parameter(description = "page", required = true) @RequestParam(value = "page") long page,
                               @Parameter(description = "size", required = true) @RequestParam(value = "size") long size,
                               @Parameter(description = "station", required = true) @PathVariable(value = "station") String station){

        log.debug("page: {}, size: {}, subwayId: {}", page, size, station);


        var str = publicClient.getRealTimeSubway(page , size , station);
        return str;
    }
}
