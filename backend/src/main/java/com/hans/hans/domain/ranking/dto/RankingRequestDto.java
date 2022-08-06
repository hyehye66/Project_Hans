package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Getter
public class RankingRequestDto {

    private Member member;

    private Mode mode;

    @Builder
    public RankingRequestDto(Member member, Mode mode){
        this.member = member;
        this.mode = mode;
    }

    public Ranking toEntity(){
        return Ranking.builder()
                .score(0L)
                .tier("Bronze")
                .member(this.member)
                .mode(this.mode)
                .build();
    }

}
