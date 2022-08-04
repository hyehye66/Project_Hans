package com.hans.hans.domain.member.dto;

import com.hans.hans.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class MemberRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;
    private String introduction;

    private String refreshToken;

    @NotBlank(message = "로그인 제공사는 필수 입력값입니다.")
    private String provider;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .introduction(this.introduction)
                .refreshToken(refreshToken)
                .provider(provider)
                .build();
    }

}
