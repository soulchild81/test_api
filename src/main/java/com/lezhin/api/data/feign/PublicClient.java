package com.lezhin.api.data.feign;


import com.lezhin.api.data.result.XmlResult;
import com.lezhin.common.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "publicClient", url = "${spring.cloud.openfeign.public-data.url}")
public interface PublicClient {

    @GetMapping(value = "/" + Constants.PUBLIC_API_KEY + "/xml/realtimeStationArrival/{page}/{size}/{station}", produces = "application/json")
    XmlResult getRealTimeSubway(@PathVariable(name = "page") long page,
                                @PathVariable(name = "size") long size,
                                @PathVariable(name = "station") String station
    );
}
