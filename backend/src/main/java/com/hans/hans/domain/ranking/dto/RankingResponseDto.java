package com.hans.hans.domain.ranking.dto;

import com.hans.hans.domain.ranking.entity.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class RankingResponseDto {

    private Page<Ranking> rankings;

}
