package com.hans.hans.domain.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GoogleMemberInfoResponseDto {

    private String sub;
    private String picture;
    private String email;

    @JsonProperty("email_verified")
    private String emailVerified;
}
