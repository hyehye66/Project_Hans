package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingSearchResponseDto;
import com.hans.hans.domain.ranking.dto.RankingsResponseDto;
import com.hans.hans.global.enumerate.Modes;
import org.springframework.data.domain.Pageable;

public interface RankingService {
    RankingsResponseDto getRankingListByMode(int modeSequence, Pageable pageable);
    RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto);
    RankingSearchResponseDto searchRanking(String nickname, int modeSequence);
    void updateRanking(String nickname, Modes modes, Long score);

}
