package com.lezhin.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiUtils {
    public static String removeSpecialChar(String str){
        // 정규표현식을 사용하여 특수문자 제거
        String newStr = str.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "");
        log.debug("current:" + str);
        log.debug("new:" + newStr);
        return newStr;
    }
}
