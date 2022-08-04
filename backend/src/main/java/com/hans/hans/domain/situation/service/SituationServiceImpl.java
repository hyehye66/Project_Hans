package com.hans.hans.domain.situation.service;

import com.hans.hans.domain.situation.dto.SituationResponseDto;
import com.hans.hans.domain.situation.entity.Situation;
import com.hans.hans.domain.situation.repository.SituationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SituationServiceImpl implements SituationService{

    private final SituationRepository situationRepository;

    @Override
    public SituationResponseDto getSituationList() {

        SituationResponseDto situations = new SituationResponseDto(situationRepository.findAll());
        return situations;
    }
}
