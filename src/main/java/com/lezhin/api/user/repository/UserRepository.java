package com.lezhin.api.user.repository;

import com.lezhin.api.user.model.AdultContentSearchUser;
import com.lezhin.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfo , Long> {
    public UserInfo findByUserSeq(long userSeq);

    @Modifying
    @Query(nativeQuery = true,
           value="select ia.user_seq as userSeq , ia.user_name as userName , ia.user_email as userEmail , ia.gender as gender , ia.type as type , ia.search_cnt as searchCnt \n" +
                   "from ( \n" +
                   "select c.* , count(*) as search_cnt \n" +
                   "from content_viewer a, \n" +
                   "     content b, \n" +
                   "     user_info c \n" +
                   "where a.content_seq = b.content_seq \n" +
                   "and a.user_seq = c.user_seq \n" +
                   "and c.created_date  >=  CURRENT_DATE - INTERVAL '7' DAY \n" +
                   "and b.adult_content = 'Y' \n" +
                   "group by a.user_seq) ia \n" +
                   "where ia.search_cnt >= 3 \n" +
                   "order by ia.search_cnt desc;")
    public List<AdultContentSearchUser> findAdultSearchUser(int days);

}
