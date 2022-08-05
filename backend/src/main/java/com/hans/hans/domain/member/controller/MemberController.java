package com.hans.hans.domain.member.controller;

import com.hans.hans.domain.login.service.LoginService;
import com.hans.hans.domain.member.dto.MemberRequestDto;
import com.hans.hans.domain.member.dto.MemberResponseDto;
import com.hans.hans.domain.member.dto.MemberUpdateRequestDto;
import com.hans.hans.domain.member.service.MemberService;
import com.hans.hans.global.response.CommonResponse;
import com.hans.hans.global.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    private final LoginService loginService;

    // 회원정보 확인
    @GetMapping()
    public ResponseEntity<?> getMemberInfo(HttpServletRequest request){
        String email = (String) request.getAttribute("email");
        MemberResponseDto memberResponseDto = memberService.getMemberInfo(email);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("회원정보 조회에 성공하였습니다.",memberResponseDto));
    }

    /*
       [회원가입]
       MemberUpdateRequestDto : nickname, introduction
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberRequestDto memberRequestDto){

        String email = memberRequestDto.getEmail();

        String accessToken = jwtService.createAccessToken(email);
        String refreshToken = jwtService.createRefreshToken();

        HttpHeaders headers = loginService.createTokenHeader(accessToken, refreshToken);

        MemberResponseDto memberResponseDto = memberService.createMember(memberRequestDto, refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(CommonResponse.createSuccess("회원가입이 완료되었습니다.",memberResponseDto));
    }

    /*
       [회원정보 수정]
       MemberUpdateRequestDto : nickname, introduction
     */
    @PutMapping()
    public ResponseEntity<?> updateMemberInfo(HttpServletRequest request, @Valid @RequestBody MemberUpdateRequestDto memberUpdateRequestDto){
        String email = (String) request.getAttribute("email");

        MemberResponseDto memberResponseDto = memberService.updateMemberInfo(email, memberUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(CommonResponse.createSuccess("회원정보 수정이 완료되었습니다.",memberResponseDto));
    }

    // 회원탈퇴
    @DeleteMapping()
    public ResponseEntity<?> deleteMember(HttpServletRequest request){
        String email = (String) request.getAttribute("email");

        memberService.deleteMember(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CommonResponse.createSuccess("회원탈퇴가 완료되었습니다.",null));
    }

}