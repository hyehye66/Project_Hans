package com.hans.hans.domain.member.service;

import com.hans.hans.domain.member.dto.*;
import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.member.repository.MemberRepository;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.mode.repository.ModeRepository;
import com.hans.hans.domain.ranking.dto.RankingRequestDto;
import com.hans.hans.domain.ranking.service.RankingServiceImpl;
import com.hans.hans.global.exception.ExistNicknameException;
import com.hans.hans.global.exception.NoExistMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final ModeRepository modeRepository;
    private final RankingServiceImpl rankingServiceImpl;

    @Override
    // 회원정보 확인
    public MemberInfoResponseDto getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        MemberInfoResponseDto memberInfoResponseDto = new MemberInfoResponseDto(member);

        return memberInfoResponseDto;
    }

    // 회원가입
    @Override
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto, String refreshToken){
        try{
            memberRequestDto.updateRefreshToken(refreshToken);
            Member member = memberRepository.save(memberRequestDto.toEntity());
            for (int i=1;i<=3;i++){
                Mode mode = modeRepository.findByModeSequence(i);
                RankingRequestDto rankingRequestDto = new RankingRequestDto(member, mode);
                rankingServiceImpl.createRanking(rankingRequestDto);
            }
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            return memberResponseDto;
        }catch (DataIntegrityViolationException e){
            throw new ExistNicknameException("존재하는 닉네임입니다.");
        }
    }

    // 회원정보 수정
    @Override
    public MemberResponseDto updateMemberInfo(String email, MemberUpdateRequestDto memberUpdateRequestDto){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        try{
            member.updateInfo(memberUpdateRequestDto.getNickname(), memberUpdateRequestDto.getIntroduction());
            memberRepository.save(member);
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            return memberResponseDto;
        }catch (DataIntegrityViolationException e){
            throw new ExistNicknameException("존재하는 닉네임입니다.");
        }
    }

    // 회원탈퇴
    @Override
    public void deleteMember(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        memberRepository.delete(member);
    }

    @Override
    public void updateRefreshToken(String email, String refreshToken) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);
    }
}