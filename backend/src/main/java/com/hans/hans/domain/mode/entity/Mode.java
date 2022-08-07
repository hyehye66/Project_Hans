package com.hans.hans.domain.mode.entity;

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
    private int modeSequence;

    @Column(name = "MODE_NAME", nullable = false)
    private String modeName;



    @Builder
    public Mode(int modeSequence){
        this.modeSequence = modeSequence;
    }
}