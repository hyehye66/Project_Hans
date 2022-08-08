package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingsResponseDto;
import org.springframework.data.domain.Pageable;

public interface RankingService {
    RankingsResponseDto getRankingListByMode(int id, Pageable pageable);
    RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto);
    RankingResponseByMemberDto searchRanking(String nickname, int id);

}
