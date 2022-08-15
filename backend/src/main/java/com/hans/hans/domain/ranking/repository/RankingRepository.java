package com.hans.hans.domain.ranking.repository;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.ranking.entity.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Page<Ranking> findRankingsByModeOrderByScoreDesc(Mode mode, Pageable pageable);
    List<Ranking> findRankingsByModeOrderByScoreDesc(Mode mode);
    Ranking findRankingByMemberAndMode(Member member, Mode mode);

}
