package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.Getter;

@Getter
public class RankingResponseByMemberDto {

    private Long score;
    private String tier;
    private Mode mode;

    public RankingResponseByMemberDto(Ranking ranking) {

        this.score  = ranking.getScore();
        this.tier = ranking.getTier();
        this.mode = ranking.getMode();

    }
}
