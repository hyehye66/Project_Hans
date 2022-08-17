package com.hans.hans.domain.dictation.service;

import com.hans.hans.domain.dictation.dto.DictationResponseDto;
import com.hans.hans.domain.dictation.entity.Dictation;
import com.hans.hans.domain.dictation.repository.DictationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictationServiceImpl implements DictationService{
    private final DictationRepository dictationRepository;

    @Override
    public DictationResponseDto getDictationList(){
        DictationResponseDto dictationResponseDto = new DictationResponseDto(dictationRepository.findAll());
        return dictationResponseDto;
    }
}
