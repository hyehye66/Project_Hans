package com.hans.hans.domain.word.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "WORDS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORD_SEQ")
    private Long wordSequence;

    @Column(name = "WORD", nullable = false)
    private String word;

    @Column(name="MEAN", nullable = false)
    private String mean;

    @Column(name="DIFFICULTY", nullable = false)
    private Long difficulty;


}
