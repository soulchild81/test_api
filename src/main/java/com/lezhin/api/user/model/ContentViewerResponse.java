package com.lezhin.api.user.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class ContentViewerResponse {
    private long contentSeq;
    private long userSeq;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
