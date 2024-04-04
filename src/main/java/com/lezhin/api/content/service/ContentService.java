package com.lezhin.api.content.service;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.api.content.model.PaymentUpdateRequest;
import com.lezhin.api.evaluation.model.LikeRankResponse;
import com.lezhin.entity.Content;

import java.util.List;

public interface ContentService {
    public ContentResponse getContent(long contentSeq);

    public Content updateContentPayment(PaymentUpdateRequest request);

    public List<ContentResponse> getContentList(List<Long> contentSeq);
}
