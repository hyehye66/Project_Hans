package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.Getter;


@Getter
public class RankingSearchResponseDto {
    private String nickname;
    private Long score;
    private String tier;
    private Mode mode;
    private int rank;


    public RankingSearchResponseDto (Ranking ranking, int rank) {
        this.nickname = ranking.getMember().getNickname();
        this.score  = ranking.getScore();
        this.tier = ranking.getTier();
        this.mode = ranking.getMode();
        this.rank = rank;
    }
}
