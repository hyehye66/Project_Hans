package com.hans.hans.domain.member.dto;

import com.hans.hans.domain.member.entity.Member;

import lombok.Getter;


@Getter
public class MemberSignUpResponseDto {
    private String nickname;
    private String email;
    private String introduction;


    public MemberSignUpResponseDto(Member entity){
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.introduction = entity.getIntroduction();
    }
}
