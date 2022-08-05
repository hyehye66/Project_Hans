package com.hans.hans.domain.login.service;

import com.hans.hans.domain.login.dto.GoogleTokenResponseDto;
import org.springframework.http.HttpHeaders;

public interface LoginService {
    GoogleTokenResponseDto getGoogleAccessToken(String code);
    String getGoogleEmail(String accessToken);
    HttpHeaders createTokenHeader(String accessToken ,String refreshToken);
}