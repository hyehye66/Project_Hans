package com.hans.hans.domain.login.controller;

import com.hans.hans.domain.login.dto.GoogleTokenResponseDto;
import com.hans.hans.domain.login.service.LoginService;
import com.hans.hans.domain.member.dto.MemberResponseDto;
import com.hans.hans.domain.member.service.MemberService;
import com.hans.hans.global.exception.NoExistMemberException;
import com.hans.hans.global.response.CommonResponse;
import com.hans.hans.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;

    private final JwtService jwtService;
    @Value("${google.login.url}")
    private String GOOGLE_OAUTH_URI;

    @GetMapping("/google-login")
    public void redirectGoogleLoginUri(HttpServletResponse response) throws IOException {
        response.sendRedirect(GOOGLE_OAUTH_URI);
    }

    @GetMapping("/login/google-oauth")
    public ResponseEntity<?> getGoogleAuthorizationCode(@RequestParam String code){
        GoogleTokenResponseDto googleTokenResponseDto = loginService.getGoogleAccessToken(code);
        String email = loginService.getGoogleEmail(googleTokenResponseDto.getAccessToken());

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