package com.hans.hans.domain.member.dto;

import com.hans.hans.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class MemberUpdateRequestDto {
    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;
    private String introduction;

    public Member toEntity(){
        return Member.builder()
                .nickname(this.nickname)
                .introduction(this.introduction)
                .build();
    }
}
