package com.hans.hans.domain.ranking.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "MODE")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODE_SEQ")
    private Long modeSequence;

    @Column(name = "MODE_NAME")
    private String mode;
}
