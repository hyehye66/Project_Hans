package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingResponseDto;
import org.springframework.data.domain.Pageable;

public interface RankingService {
    RankingResponseDto getRankingListByMode(int id, Pageable pageable);
    RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto);

}
