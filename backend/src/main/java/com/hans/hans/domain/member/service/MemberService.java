package com.hans.hans.domain.member.service;


import com.hans.hans.domain.member.dto.*;

public interface MemberService {

    MemberResponseDto createMember(MemberRequestDto memberRequestDto, String refreshToken);
    MemberResponseDto updateMemberInfo(String email, MemberUpdateRequestDto memberUpdateRequestDto);
    MemberInfoResponseDto getMemberInfo(String email);
    void deleteMember(String email);

    void updateRefreshToken(String email, String refreshToken);

}
