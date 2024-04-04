package com.lezhin.common.response;

import java.time.ZonedDateTime;

public interface ApiBaseResponse {

    String getCode();

    String getMessage();

    ZonedDateTime getTimestamp();
}
