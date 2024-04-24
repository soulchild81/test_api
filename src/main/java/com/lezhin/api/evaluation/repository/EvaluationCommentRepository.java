package com.lezhin.api.evaluation.repository;

import com.lezhin.entity.ContentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationCommentRepository extends JpaRepository<ContentComment , Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM soulchild_content.content_comment WHERE user_seq = :userSeq")
    public void removeCommentUser(@Param("userSeq") long userSeq);
}
