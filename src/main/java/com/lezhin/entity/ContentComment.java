package com.lezhin.entity;

import com.lezhin.entity.entityKey.ContentUserKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@SQLDelete(sql="DELETE FROM CONTENT_COMMENT WHERE user_seq = ?")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ContentComment {
    @EmbeddedId
    private ContentUserKey key;

    private String comment;
}
