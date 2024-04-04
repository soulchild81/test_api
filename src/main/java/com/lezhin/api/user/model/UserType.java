package com.lezhin.api.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserType {
    NORMAL("NORMAL" , "일반"),
    ADULT("ADULT" , "성인")
    ;

    final String desc;
    final String descKor;
}
