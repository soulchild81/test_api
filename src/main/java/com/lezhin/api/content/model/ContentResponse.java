package com.lezhin.api.content.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema
@Setter
@Getter
@Builder
public class ContentResponse {
    @Schema(description = "컨텐츠 번호")
    private Long contentSeq;
    @Schema(description = "컨텐츠 명")
    private String contentsName;
    @Schema(description = "작가")
    private String author;
    @Schema(description = "구매 금액")
    private BigDecimal coin;
    @Schema(description = "컨텐츠 오픈일")
    private LocalDateTime openDate;
    @Schema(description = "좋아요")
    private int likeCount;
    @Schema(description = "싫어요")
    private int disLikeCount;
}
