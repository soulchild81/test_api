package com.lezhin.api.evaluation.model;

import lombok.*;

public interface EvaluationCount {
    Long getContentSeq();
    Integer getLikeCount();
    Integer getDisLikeCount();
}