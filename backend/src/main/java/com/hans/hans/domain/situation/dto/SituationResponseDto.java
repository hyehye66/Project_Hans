package com.hans.hans.domain.situation.dto;


import com.hans.hans.domain.situation.entity.Situation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SituationResponseDto {
    private List<Situation> situations;
}
