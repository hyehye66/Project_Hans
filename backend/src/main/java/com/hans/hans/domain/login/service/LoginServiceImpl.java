package com.hans.hans.domain.login.service;

import com.hans.hans.domain.login.dto.GoogleMemberInfoResponseDto;
import com.hans.hans.domain.login.dto.GoogleTokenResponseDto;
import com.hans.hans.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Override
    public HttpHeaders createTokenHeader(String accessToken, String refreshToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);
        headers.add("refreshToken",refreshToken);

        return headers;
    }
}