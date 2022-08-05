package com.hans.hans.domain.ranking.entity;


import lombok.*;

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

    @Column(name = "MODE_NAME")
    private String modeName;

}
