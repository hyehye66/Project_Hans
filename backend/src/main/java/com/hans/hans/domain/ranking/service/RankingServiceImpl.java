package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.member.repository.MemberRepository;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.mode.repository.ModeRepository;
import com.hans.hans.domain.ranking.dto.*;
import com.hans.hans.domain.ranking.entity.Ranking;
import com.hans.hans.domain.ranking.repository.RankingRepository;
import com.hans.hans.global.enumerate.Modes;
import com.hans.hans.global.exception.NoExistRankingListSearchByNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final ModeRepository modeRepository;
    private final MemberRepository memberRepository;

    @Override
    public RankingsResponseDto getRankingListByMode(int modeSequence, Pageable pageable) {
        Mode mode = modeRepository.findByModeSequence(modeSequence);
        Page<Ranking> list = rankingRepository.findRankingsByModeOrderByScoreDesc(mode, pageable);
        // RankingResponseDto로 변환
        Page<RankingResponseDto> map = list.map(ranking -> new RankingResponseDto(ranking));
        RankingsResponseDto rankings = new RankingsResponseDto(map);
        return rankings;
    }

    @Override
    public RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto) {
        Ranking ranking = rankingRepository.save(rankingRequestDto.toEntity());
        RankingResponseByMemberDto rankingResponseByMemberDto = new RankingResponseByMemberDto(ranking);
        return rankingResponseByMemberDto;
    }

    @Override
    public RankingSearchResponseDto searchRanking(String nickname, int modeSequence) {
        Member member = memberRepository.findByNickname(nickname);

        Mode mode = modeRepository.findByModeSequence(modeSequence);
        Ranking ranking = rankingRepository.findRankingByMemberAndMode(member, mode);
        
        if(ranking==null) throw new NoExistRankingListSearchByNicknameException("현재 검색한 닉네임의 사용자는 랭킹 리스트에 등록되어있지 않습니다.");

        List<Ranking> lists = rankingRepository.findRankingsByModeOrderByScoreDesc(mode);
        int rank = lists.indexOf(ranking)+1;

        
        RankingSearchResponseDto rankingSearchResponseDto = new RankingSearchResponseDto(ranking,rank);
        return rankingSearchResponseDto;
    }

    @Override
    public void updateRanking(String nickname, Modes modes, Long score){
        Member member = memberRepository.findByNickname(nickname);

        Mode mode = modeRepository.findByModeSequence(modes.getModeSequence());

        Ranking ranking = rankingRepository.findRankingByMemberAndMode(member, mode);

        Long newScore = score + ranking.getScore();

        ranking.updateScore(newScore);
        ranking.updateTier(newScore);

        rankingRepository.save(ranking);
    }

}
