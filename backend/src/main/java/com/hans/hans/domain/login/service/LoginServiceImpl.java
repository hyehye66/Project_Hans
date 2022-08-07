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

    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${google.client.secret}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${google.redirect.uri}")
    private String GOOGLE_REDIRECT_URI;

    private final String GOOGLE_GRANT_TYPE = "authorization_code";

    private final JwtService jwtService;

    @Override
    //Authorization code -> accessToken, refreshToken 받아오기
    public GoogleTokenResponseDto getGoogleAccessToken(String code){
        URI uri = UriComponentsBuilder.fromUriString("https://oauth2.googleapis.com")
                .path("/token")
                .encode()
                .build()
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("code",code);
        params.add("client_id",GOOGLE_CLIENT_ID);
        params.add("client_secret",GOOGLE_CLIENT_SECRET);
        params.add("redirect_uri",GOOGLE_REDIRECT_URI);
        params.add("grant_type",GOOGLE_GRANT_TYPE);

        HttpEntity<MultiValueMap<String,String>> restRequest = new HttpEntity<>(params,headers);
        return new RestTemplate().postForEntity(uri,restRequest, GoogleTokenResponseDto.class).getBody();
    }

    @Override
    //accessToken -> userInfo의 email 받아오기
    public String getGoogleEmail(String accessToken){
        URI uri = UriComponentsBuilder.fromUriString("https://www.googleapis.com")
                .path("/oauth2/v3/userinfo")
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

        return new RestTemplate().exchange(uri, HttpMethod.GET, request, GoogleMemberInfoResponseDto.class).getBody().getEmail();
    }

    @Override
    public HttpHeaders createTokenHeader(String accessToken, String refreshToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);
        headers.add("refreshToken",refreshToken);

        return headers;
    }
}