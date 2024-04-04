package com.lezhin.api.user.model;


public interface AdultContentSearchUser {

    long getUserSeq();
    String getUserName();
    String getUserEmail();
    String getGender();
    String getType();

    int getSearchCnt();

}