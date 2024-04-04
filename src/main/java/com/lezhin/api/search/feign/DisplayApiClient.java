package com.lezhin.api.search.feign;

import com.lezhin.api.search.result.LotteOnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "displayApiClient", url = "${spring.cloud.openfeign.display.url}")
public interface DisplayApiClient {

    @GetMapping(value = "?delivery_type=ts&sort=ranking&category_type=display", produces = "application/json")
    LotteOnResult getCategoryProduct(@RequestParam(name = "category_id") String categoryId,
                                     @RequestParam(name = "page") int page,
                                     @RequestParam(name = "size") int size);
}