package com.hans.hans.domain.ranking.entity;


import com.hans.hans.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "RANKING")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RANKING_SEQ")
    private Long rankingSequence;

    @Column(name = "SCORE")
    private Long score;

    @ManyToOne
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

}
