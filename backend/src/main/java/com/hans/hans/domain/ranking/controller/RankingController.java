package com.hans.hans.domain.ranking.controller;

import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingsResponseDto;
import com.hans.hans.domain.ranking.service.RankingServiceImpl;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/rankings")
@RequiredArgsConstructor
public class RankingController {
    private final RankingServiceImpl rankingServiceImpl;

    @GetMapping("/{modeId}")
    public ResponseEntity<?> getRankingListByMode(@PathVariable(name="modeId") int id, @PageableDefault(size=10)Pageable pageable) {
        RankingsResponseDto rankingsResponseDto = rankingServiceImpl.getRankingListByMode(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("랭킹 조회에 성공하였습니다.",rankingsResponseDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchRanking(String nickname, int id) {
        RankingResponseByMemberDto rankingResponseByMemberDto = rankingServiceImpl.searchRanking(nickname, id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("랭킹 검색에 성공했습니다.", rankingResponseByMemberDto));
    }


}
