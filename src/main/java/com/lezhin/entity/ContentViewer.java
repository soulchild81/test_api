package com.lezhin.entity;

import com.lezhin.entity.base.BaseTimeEntity;
import com.lezhin.entity.entityKey.ContentUserKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class ContentViewer extends BaseTimeEntity {
    @EmbeddedId
    public ContentUserKey viewerKey;

}
