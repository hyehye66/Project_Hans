package com.hans.hans.domain.member.entity;

import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "MEMBER")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "member")
    List<Ranking> rankings = new ArrayList<Ranking>();

    @Builder
    public Member(String nickname, String email, String provider, String introduction, String refreshToken){
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

