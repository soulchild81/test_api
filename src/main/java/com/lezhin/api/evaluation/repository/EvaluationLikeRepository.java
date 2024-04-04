package com.lezhin.api.evaluation.repository;

import com.lezhin.api.evaluation.model.EvaluationCount;
import com.lezhin.entity.ContentLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface EvaluationLikeRepository extends JpaRepository<ContentLike, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM CONTENT_LIKE where user_seq = :userSeq")
    public void removeLikeUser(@Param("userSeq") long userSeq);
    @Query(nativeQuery = true, value = "SELECT count(content_seq) as likeCount FROM CONTENT_LIKE where content_seq = :contentSeq")
    public EvaluationCount findContentLikeCount(@Param("contentSeq") long contentSeq);

    @Query(nativeQuery = true, value = " SELECT ia.content_seq as contentSeq, ia.like_count as likeCount FROM (SELECT cl.content_seq as content_seq, count(*) as like_count FROM content_like cl GROUP BY cl.content_seq) as ia ORDER BY ia.like_count desc , ia.content_seq asc")
    public List<EvaluationCount> findRank(Pageable pageable);
}