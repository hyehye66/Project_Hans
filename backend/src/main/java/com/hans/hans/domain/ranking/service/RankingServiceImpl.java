package com.hans.hans.domain.ranking.service;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.member.repository.MemberRepository;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.mode.repository.ModeRepository;
import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.dto.RankingResponseByMemberDto;
import com.hans.hans.domain.ranking.dto.RankingsResponseDto;
import com.hans.hans.domain.ranking.entity.Ranking;
import com.hans.hans.domain.ranking.repository.RankingRepository;
import com.hans.hans.global.exception.NoExistMemberException;
import com.hans.hans.global.exception.NoExistRankingListSearchByNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;
    private final ModeRepository modeRepository;
    private final MemberRepository memberRepository;

    @Override
    public RankingsResponseDto getRankingListByMode(int id, Pageable pageable) {
        Mode mode = modeRepository.findByModeSequence(id);
        Page<Ranking> list = rankingRepository.findRankingsByModeOrderByScoreDesc(mode, pageable);
        RankingsResponseDto rankings = new RankingsResponseDto(list);
        return rankings;
    }

    @Override
    public RankingResponseByMemberDto createRanking(RankingRequestDto rankingRequestDto) {
        Ranking ranking = rankingRepository.save(rankingRequestDto.toEntity());
        RankingResponseByMemberDto rankingResponseByMemberDto = new RankingResponseByMemberDto(ranking);
        return rankingResponseByMemberDto;
    }

    @Override
    public RankingResponseByMemberDto searchRanking(String nickname, int id) {
        Member member = memberRepository.findByNickname(nickname);

        Mode mode = modeRepository.findByModeSequence(id);
        Ranking ranking = rankingRepository.findRankingByMemberAndMode(member, mode);
        if(ranking==null) throw new NoExistRankingListSearchByNicknameException("현재 검색한 닉네임의 사용자는 랭킹 리스트에 등록되어있지 않습니다.");

        RankingResponseByMemberDto rankingResponseByMemberDto = new RankingResponseByMemberDto(ranking);

        return rankingResponseByMemberDto;
    }

}
