package com.hans.hans.domain.ranking.entity;

import com.hans.hans.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "RANKINGS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RANKING_SEQ")
    private Long rankingSequence;

    @Column(name = "SCORE", nullable = false)
    private Long score;

    @ManyToOne
    @JoinColumn(name = "MODE_SEQ")
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

}
