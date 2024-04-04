package com.lezhin.api.content.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentType {
    FREE("free", "무료"),
    PAID("paid" ,"유료")
    ;

    final String code;
    final String desc;
}
