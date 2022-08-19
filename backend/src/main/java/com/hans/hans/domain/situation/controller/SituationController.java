package com.hans.hans.domain.situation.controller;

import com.hans.hans.domain.situation.dto.SituationResponseDto;
import com.hans.hans.domain.situation.service.SituationServiceImpl;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/situations")
@RequiredArgsConstructor
public class SituationController {

    private final SituationServiceImpl situationServiceImpl;

    @GetMapping()
    public ResponseEntity<?> getSituationList() {

        SituationResponseDto situationResponseDto = situationServiceImpl.getSituationList();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("상황별 한국어 문장 조회에 성공하였습니다.",situationResponseDto));

    }

}


