package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.ranking.dto.RankingResponseDto;
import org.springframework.data.domain.Pageable;

public interface RankingService {
    RankingResponseDto getRankingListByMode(Long id, Pageable pageable);

}
