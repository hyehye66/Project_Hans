package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingResponseDto {
    private Long rankingSequence;
    private Long score;
    private String tier;
    private String nickname;
    private Mode mode;

    public RankingResponseDto(Ranking ranking){
        this.rankingSequence = ranking.getRankingSequence();
        this.score = ranking.getScore();
        this.tier = ranking.getTier();
        this.nickname = ranking.getMember().getNickname();
        this.mode = ranking.getMode();
    }

}
