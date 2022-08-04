package com.hans.hans.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "MEMBERS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    private Long memberSequence;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "NICKNAME", unique = true, nullable = false)
    private String nickname;

    @Column(name = "PROVIDER", nullable = false)
    private String provider;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    @Builder
    public Member(Long memberSequence, String nickname, String email, String provider, String introduction, String refreshToken){
        this.memberSequence = memberSequence;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
        this.introduction = introduction;
        this.refreshToken = refreshToken;
    }

    public void updateInfo(String nickname, String introduction){
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}

