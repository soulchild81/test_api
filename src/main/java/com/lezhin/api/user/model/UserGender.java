package com.lezhin.api.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserGender {
    MALE("M"),
    FEMALE("F")
    ;
    final String code;

    public static UserGender of(String value) {
        for (UserGender gender : values()) {
            if (gender.getCode().equals(value)) {
                return gender;
            }
        }

        throw new IllegalArgumentException("invalid type : " + value);
    }
}
