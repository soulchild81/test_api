package com.lezhin.api.user.service;

import com.lezhin.api.user.model.ContentViewerResponse;
import com.lezhin.api.user.model.UserInfoResponse;
import com.lezhin.entity.ContentViewer;

import java.util.List;

public interface UserService {
    public UserInfoResponse getUserinfo(long userSeq);

    public ContentViewer getContentViewer(long contentSeq , long userSeq);

    public boolean addContentView(long contentSeq , long userSeq);

    public List<ContentViewerResponse> getContentViewList(long contentSeq , int start , int length);

    public List<UserInfoResponse> getAdultSearchUserList(int days);

    public void removeUser(long userSeq);
}
