package com.lezhin.api.content.repository;

import com.lezhin.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    public Content findByContentSeq(Long contentSeq);

    public List<Content> findByContentSeqIn(List<Long> contentRepository);

}
