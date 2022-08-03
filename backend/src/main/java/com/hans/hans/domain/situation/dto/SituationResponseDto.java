package com.hans.hans.domain.situation.dto;

import com.hans.hans.domain.situation.entity.Situation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SituationResponseDto {

        private List<Situation> situations;

}
