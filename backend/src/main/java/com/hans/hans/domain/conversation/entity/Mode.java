package com.hans.hans.domain.conversation.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "MODES")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODE_SEQ")
    private Long modeSequence;

    @Column(name = "MODE_NAME", nullable = false)
    private String modeName;

    @Builder
    public Mode(Long modeSequence){
        this.modeSequence = modeSequence;
    }
}