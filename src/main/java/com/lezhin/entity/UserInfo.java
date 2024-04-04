package com.lezhin.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lezhin.entity.base.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class UserInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userSeq;

    private String userName;

    private String userEmail;

    private String gender;

    private String type;
}
