package com.lezhin.entity;

import com.lezhin.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long contentSeq;

    private String contentsName;

    private String author;

    private String adultContent;

    private BigDecimal coin;

    private LocalDateTime openDate;

    @Transient
    private int likeCount;
    @Transient
    private int disLikeCount;

}
