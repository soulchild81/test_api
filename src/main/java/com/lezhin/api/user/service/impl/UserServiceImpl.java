package com.lezhin.api.user.service.impl;

import com.lezhin.api.evaluation.repository.EvaluationCommentRepository;
import com.lezhin.api.evaluation.repository.EvaluationDisLikeRepository;
import com.lezhin.api.evaluation.repository.EvaluationLikeRepository;
import com.lezhin.api.user.mapper.UserMapper;
import com.lezhin.api.user.model.AdultContentSearchUser;
import com.lezhin.api.user.model.ContentViewerResponse;
import com.lezhin.api.user.model.UserInfoResponse;
import com.lezhin.api.user.repository.ContentViewerRepository;
import com.lezhin.api.user.repository.UserRepository;
import com.lezhin.api.user.service.UserService;
import com.lezhin.entity.ContentViewer;
import com.lezhin.entity.UserInfo;
import com.lezhin.entity.entityKey.ContentUserKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ContentViewerRepository contentViewerRepository;
    private final EvaluationLikeRepository evaluationLikeRepository;
    private final EvaluationDisLikeRepository evaluationDisLikeRepository;
    private final EvaluationCommentRepository evaluationCommentRepository;


    @Override
    public UserInfoResponse getUserinfo(long userSeq){
        UserInfo user = userRepository.findByUserSeq(userSeq);
        return UserMapper.INSTANCE.toUserInfoResponse(user);
    }

    @Override
    public ContentViewer getContentViewer(long contentSeq , long userSeq){
        ContentUserKey key = ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build();
        return contentViewerRepository.findContentViewerByViewerKey(key);
    }

    @Override
    public boolean addContentView(long contentSeq , long userSeq){
        boolean result = true;
        try{
            ContentViewer viewer = ContentViewer.builder()
                    .viewerKey(ContentUserKey.builder().contentSeq(contentSeq).userSeq(userSeq).build())
                    .build();
            contentViewerRepository.save(viewer);
        }catch(Exception e){
            result = false;
        }
        return result;
    }

    @Override
    public List<ContentViewerResponse> getContentViewList(long contentSeq , int start , int length){
        Pageable pageable = PageRequest.of(start, length);
        List<ContentViewer> list = contentViewerRepository.findContentViewerByViewerKey_ContentSeq(contentSeq , pageable);
        List<ContentViewerResponse> responseList = list.stream().map(UserMapper.INSTANCE::toContentViewerResponse).toList();
        return responseList;
    }

    @Override
    public List<UserInfoResponse> getAdultSearchUserList(int days){
        List<AdultContentSearchUser> list = userRepository.findAdultSearchUser(days);
        return list.stream().map(UserMapper.INSTANCE::toUserInfoResponse).toList();
    }

    @Override
    @Transactional
    public void removeUser(long userSeq){
        // 평가 삭제
        evaluationLikeRepository.removeLikeUser(userSeq);
        evaluationDisLikeRepository.removeDisLikeUser(userSeq);
        evaluationCommentRepository.removeCommentUser(userSeq);

        // 조회 이력 삭제
        contentViewerRepository.removeViewerUser(userSeq);

        // 유저 정보 삭제
        UserInfo info = userRepository.findByUserSeq(userSeq);
        userRepository.delete(info);
    }
}
