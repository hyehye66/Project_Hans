package com.hans.hans.domain.dictation.service;

import com.hans.hans.domain.dictation.dto.DictationResponseDto;


public interface DictationService {
    DictationResponseDto getDictationList(Long difficulty);
}
