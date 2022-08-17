package com.hans.hans.domain.dictation.dto;

import com.hans.hans.domain.dictation.entity.Dictation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DictationResponseDto {

    private List<Dictation> dictations;

}

