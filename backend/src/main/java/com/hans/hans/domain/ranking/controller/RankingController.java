package com.hans.hans.domain.ranking.controller;

import com.hans.hans.domain.ranking.dto.RankingResponseDto;
import com.hans.hans.domain.ranking.service.RankingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/rankings")
@RequiredArgsConstructor
public class RankingController {
    private final RankingServiceImpl rankingServiceImpl;

    @GetMapping("/{modeId}")
    public ResponseEntity<?> getRankingListByMode(@PathVariable(name="modeId") Long id, @PageableDefault(size=10)Pageable pageable) {
        RankingResponseDto rankingResponseDto = rankingServiceImpl.getRankingListByMode(id, pageable);
        return ResponseEntity.ok(rankingResponseDto);
    }


}
