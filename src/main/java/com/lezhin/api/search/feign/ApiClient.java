package com.lezhin.api.search.feign;

import com.lezhin.api.search.result.LotteOnResult;
import com.lezhin.api.search.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apiClient", url = "${spring.cloud.openfeign.category.url}")
public interface ApiClient {

    @GetMapping(value = "", produces = "application/json")
    Result getCategory(@RequestParam(name = "u3") String depth);
}

