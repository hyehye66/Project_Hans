package com.hans.hans.domain.member.service;


import com.hans.hans.domain.member.dto.MemberRequestDto;
import com.hans.hans.domain.member.dto.MemberResponseDto;
import com.hans.hans.domain.member.dto.MemberUpdateRequestDto;

public interface MemberService {

    MemberResponseDto createMember(MemberRequestDto memberRequestDto, String refreshToken);
    MemberResponseDto updateMemberInfo(String email, MemberUpdateRequestDto memberUpdateRequestDto);
    MemberResponseDto getMemberInfo(String email);
    void deleteMember(String email);

    void updateRefreshToken(String email, String refreshToken);

}
