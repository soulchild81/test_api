package com.lezhin.api.evaluation.model;

import com.lezhin.api.content.model.ContentResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
public class LikeRankResponse {
    private List<ContentResponse> likeRanking;
    private List<ContentResponse> disLikeRanking;
}
