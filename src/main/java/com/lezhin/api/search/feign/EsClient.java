package com.lezhin.api.search.feign;

import com.lezhin.api.search.model.EsPutResult;
import com.lezhin.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EsApiClient", url = Constants.ES_URL+ ":" + Constants.ES_PORT)
public interface EsClient {

    @PutMapping(value = "/product/_doc/{product_id}", produces = "application/json" , consumes = "application/json")
    EsPutResult setIndexing(@PathVariable("product_id") String productId,
                            @RequestBody Object jsonStr);
}

