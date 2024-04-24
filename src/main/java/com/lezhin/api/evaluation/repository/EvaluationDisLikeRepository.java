package com.lezhin.api.evaluation.repository;

import com.lezhin.api.evaluation.model.EvaluationCount;
import com.lezhin.entity.ContentDisLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationDisLikeRepository  extends JpaRepository<ContentDisLike, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM soulchild_content.CONTENT_DIS_LIKE where user_seq = :userSeq")
    public void removeDisLikeUser(@Param("userSeq") long userSeq);
    @Query(nativeQuery = true, value = "SELECT count(content_seq) as disLikeCount FROM soulchild_content.CONTENT_DIS_LIKE where content_seq = :contentSeq")
    public EvaluationCount findContentDisLikeCount(@Param("contentSeq") long contentSeq);
    @Query(nativeQuery = true, value = " SELECT ia.content_seq as contentSeq, ia.like_count as likeCount FROM (SELECT cl.content_seq as content_seq, count(*) as like_count FROM soulchild_content.content_dis_like cl GROUP BY cl.content_seq) as ia ORDER BY ia.like_count desc , ia.content_seq asc")
    public List<EvaluationCount> findRank(Pageable pageable);
}
