package com.lezhin.api.evaluation.service;

import com.lezhin.api.evaluation.model.LikeRankResponse;

public interface EvaluationService {
    // 좋아요
    public boolean addContentLike(long userSeq , long contentSeq);

    public boolean removeContentLike(long userSeq , long contentSeq);

    // 싫어요
    public boolean addContentDisLike(long userSeq , long contentSeq);

    public boolean removeContentDisLike(long userSeq , long contentSeq);

    // 댓글
    public boolean addContentComment(long userSeq , long contentSeq , String comment);

    public boolean removeContentComment(long userSeq , long contentSeq);

    public LikeRankResponse getRankList(int size);
}
