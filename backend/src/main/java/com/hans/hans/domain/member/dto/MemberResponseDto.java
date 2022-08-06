package com.hans.hans.domain.member.dto;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberResponseDto {

    private String nickname;
    private String email;
    private String introduction;
    private List<RankingResponseByMemberDto> rankings;


    public MemberResponseDto(Member entity){
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.introduction = entity.getIntroduction();
        this.rankings = entity.getRankings().stream().map(RankingResponseByMemberDto::new).collect(Collectors.toList());
    }
}
