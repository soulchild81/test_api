package com.lezhin.entity.entityKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContentUserKey implements Serializable {
    private Long userSeq;
    private Long contentSeq;
}
