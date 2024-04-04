package com.lezhin.api.user.repository;

import com.lezhin.entity.ContentViewer;
import com.lezhin.entity.entityKey.ContentUserKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentViewerRepository extends JpaRepository<ContentViewer, Long> {
    public List<ContentViewer> findContentViewerByViewerKey_ContentSeq(long contentSeq , Pageable pageable);
    public ContentViewer findContentViewerByViewerKey(ContentUserKey viewerKey);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM CONTENT_VIEWER WHERE user_seq = :userSeq")
    public void removeViewerUser(@Param("userSeq") long userSeq);


}
