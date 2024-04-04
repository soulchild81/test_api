package com.lezhin.api.evaluation.service.impl;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.api.content.service.ContentService;
import com.lezhin.api.evaluation.model.EvaluationCount;
import com.lezhin.api.evaluation.model.LikeRankResponse;
import com.lezhin.api.evaluation.repository.EvaluationCommentRepository;
import com.lezhin.api.evaluation.repository.EvaluationDisLikeRepository;
import com.lezhin.api.evaluation.repository.EvaluationLikeRepository;
import com.lezhin.api.evaluation.service.EvaluationService;
import com.lezhin.entity.ContentComment;
import com.lezhin.entity.ContentDisLike;
import com.lezhin.entity.ContentLike;
import com.lezhin.entity.entityKey.ContentUserKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationLikeRepository evaluationLikeRepository;
    private final EvaluationDisLikeRepository evaluationDisLikeRepository;
    private final EvaluationCommentRepository evaluationCommentRepository;

    private final ContentService contentService;

    @Override
    public boolean addContentLike(long userSeq , long contentSeq){
        boolean result = true;
        try{
            ContentLike like = ContentLike.builder().likeKey(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).build();
            evaluationLikeRepository.save(like);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public boolean removeContentLike(long userSeq , long contentSeq){
        boolean result = true;
        try{
            final var like = ContentLike.builder().likeKey(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).build();
            evaluationLikeRepository.delete(like);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public boolean addContentDisLike(long userSeq , long contentSeq){
        boolean result = true;
        try{
            final var disLike = ContentDisLike.builder().disLikeKey(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).build();
            evaluationDisLikeRepository.save(disLike);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public boolean removeContentDisLike(long userSeq , long contentSeq){
        boolean result = true;
        try{
            final var disLike = ContentDisLike.builder().disLikeKey(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).build();
            evaluationDisLikeRepository.delete(disLike);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public boolean addContentComment(long userSeq , long contentSeq , String commentStr){
        boolean result = true;
        try{
            ContentComment comment = ContentComment.builder().key(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).comment(commentStr).build();
            evaluationCommentRepository.save(comment);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public boolean removeContentComment(long userSeq , long contentSeq){
        boolean result = true;
        try{
            final var comment = ContentComment.builder().key(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build()).build();
            evaluationCommentRepository.delete(comment);
        }catch(Exception e){
            result = false;
        }

        return result;
    }

    @Override
    public LikeRankResponse getRankList(int size){
        Pageable pageable = PageRequest.of(0, size);
        List<EvaluationCount> likeList = evaluationLikeRepository.findRank(pageable);
        List<ContentResponse> likeContentList = new ArrayList<>();
        likeList.forEach(s -> likeContentList.add(contentService.getContent(s.getContentSeq())));

        List<EvaluationCount> disLikeList = evaluationDisLikeRepository.findRank(pageable);
        List<ContentResponse> disLikeContentList = new ArrayList<>();
        disLikeList.forEach(s -> disLikeContentList.add(contentService.getContent(s.getContentSeq())));

        return LikeRankResponse.builder()
                .likeRanking(likeContentList)
                .disLikeRanking(disLikeContentList)
                .build();
    }
}
