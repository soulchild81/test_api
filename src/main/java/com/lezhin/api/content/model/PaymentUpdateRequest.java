package com.lezhin.api.content.model;

import lombok.Getter;

@Getter
public class PaymentUpdateRequest {
    private PaymentType paymentType;
    private long amount;
    private long contentSeq;
}
