package com.lezhin.entity;


import com.lezhin.entity.base.BaseTimeEntity;
import com.lezhin.entity.entityKey.ContentUserKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ContentDisLike extends BaseTimeEntity {
    @EmbeddedId
    private ContentUserKey disLikeKey;
}
