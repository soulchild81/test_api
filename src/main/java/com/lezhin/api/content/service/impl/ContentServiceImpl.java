package com.lezhin.api.content.service.impl;

import com.lezhin.api.content.mapper.ContentMapper;
import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.api.content.model.PaymentUpdateRequest;
import com.lezhin.api.content.repository.ContentRepository;
import com.lezhin.api.content.service.ContentService;
import com.lezhin.api.evaluation.repository.EvaluationDisLikeRepository;
import com.lezhin.api.evaluation.repository.EvaluationLikeRepository;
import com.lezhin.api.user.service.UserService;
import com.lezhin.entity.Content;
import com.lezhin.entity.ContentViewer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final EvaluationLikeRepository evaluationLikeRepository;
    private final EvaluationDisLikeRepository evaluationDisLikeRepository;
    private final UserService userService;

    @Override
    public ContentResponse getContent(long contentSeq){
        Content content = contentRepository.findByContentSeq(contentSeq);
        content.setLikeCount(evaluationLikeRepository.findContentLikeCount(contentSeq).getLikeCount());
        content.setDisLikeCount(evaluationDisLikeRepository.findContentDisLikeCount(contentSeq).getDisLikeCount());

        ContentViewer viewer = userService.getContentViewer(contentSeq , 1);
        if(viewer == null){
            userService.addContentView(contentSeq , 1);
        }
        return ContentMapper.INSTANCE.toContentResponse(content);
    }

    @Transactional
    public Content updateContentPayment(PaymentUpdateRequest request){
        Content content = contentRepository.findByContentSeq(request.getContentSeq());
        content.setCoin(new BigDecimal(request.getAmount()));
        return content;
    }

    @Override
    public List<ContentResponse> getContentList(List<Long> contentSeqList){
        List<Content> list = contentRepository.findByContentSeqIn(contentSeqList);
        return list.stream().map(ContentMapper.INSTANCE::toContentResponse).collect(Collectors.toList());
    }
}
