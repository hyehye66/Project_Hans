package com.hans.hans.domain.ranking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name = "Tier", nullable = false)
    private String tier;

    @ManyToOne
    @JoinColumn(name = "MODE_SEQ", nullable = false)
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "MEMBER_SEQ", nullable = false)
    @JsonBackReference
    private Member member;

    @Builder
    public Ranking(Long score, String tier, Member member, Mode mode){
        this.score = score;
        this.tier = tier;
        this.member = member;
        this.mode = mode;
    }



}
