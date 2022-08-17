package com.hans.hans.domain.dictation.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "DICTATIONS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dictation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DICTATION_SEQ")
    private Long dictationSequence;

    @Column(name = "SENTENCE", nullable = false)
    private String sentence;

    @Column(name = "DIFFICULTY", nullable = false)
    private Long difficulty;
}
