package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingsResponseDto;
import com.hans.hans.domain.ranking.entity.Ranking;
import com.hans.hans.global.enumerate.Modes;
import org.springframework.data.domain.Pageable;

public interface RankingService {
    RankingsResponseDto getRankingListByMode(int id, Pageable pageable);
    RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto);
    RankingResponseByMemberDto searchRanking(String nickname, int id);
    void updateRanking(String nickname, Modes modes, Long score);

}
