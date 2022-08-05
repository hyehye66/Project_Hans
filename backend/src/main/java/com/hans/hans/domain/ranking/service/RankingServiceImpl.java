package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.ranking.dto.RankingResponseDto;
import com.hans.hans.domain.ranking.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import com.hans.hans.domain.ranking.repository.ModeRepository;
import com.hans.hans.domain.ranking.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final ModeRepository modeRepository;

    @Override
    public RankingResponseDto getRankingListByMode(Long id, Pageable pageable) {
        Mode mode = modeRepository.findById(id).get();
        Page<Ranking> list = rankingRepository.findRankingsByModeOrderByScoreDesc(mode, pageable);
        RankingResponseDto rankings = new RankingResponseDto(list);
        return rankings;
    }

}
