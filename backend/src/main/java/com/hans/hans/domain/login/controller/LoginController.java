package com.hans.hans.domain.login.controller;

import com.hans.hans.domain.login.service.LoginService;
import com.hans.hans.domain.member.dto.MemberResponseDto;
import com.hans.hans.domain.member.service.MemberService;
import com.hans.hans.global.exception.NoExistMemberException;
import com.hans.hans.global.response.CommonResponse;
import com.hans.hans.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;

    private final JwtService jwtService;

    @GetMapping()
    public ResponseEntity<?> getGoogleAuthorizationCode(@RequestParam String email){
        try{
            memberService.getMemberInfo(email);

            String accessToken = jwtService.createAccessToken(email);
            String refreshToken = jwtService.createRefreshToken();

            HttpHeaders headers = loginService.createTokenHeader(accessToken, refreshToken);
            memberService.updateRefreshToken(email, refreshToken);

            MemberResponseDto memberResponseDto = memberService.getMemberInfo(email);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(CommonResponse.createSuccess("로그인이 완료되었습니다.",memberResponseDto));

        }catch (NoExistMemberException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CommonResponse.createSuccess("회원가입이 필요합니다.",null));
        }

    }

}