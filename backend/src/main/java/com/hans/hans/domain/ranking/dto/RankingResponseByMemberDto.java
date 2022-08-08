package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.Getter;

@Getter
public class RankingResponseByMemberDto {

    private String nickname;
    private Long score;
    private String tier;
    private Mode mode;


    public RankingResponseByMemberDto(Ranking ranking) {

        this.nickname = ranking.getMember().getNickname();
        this.score  = ranking.getScore();
        this.tier = ranking.getTier();
        this.mode = ranking.getMode();

    }
}
