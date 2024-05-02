package com.lezhin.api.content.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @Schema(description = "컨텐츠 오픈일")
    private LocalDateTime openDate;

    @Schema(description = "좋아요")
    private int likeCount;
    @Schema(description = "싫어요")
    private int disLikeCount;
}
