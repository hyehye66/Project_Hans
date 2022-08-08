package com.hans.hans.domain.situation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "SITUATIONS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Situation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SITUATION_SEQ")
    private Long situationSequence;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "SENTENCE", nullable = false)
    private String sentence;

    @Column(name = "ENGLISH_SENTENCE", nullable = false)
    private String eng_sentence;

}
