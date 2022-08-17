package com.hans.hans.domain.dictation.controller;

import com.hans.hans.domain.dictation.dto.DictationResponseDto;
import com.hans.hans.domain.dictation.service.DictationService;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictations")
@RequiredArgsConstructor
public class DictationController {

    private final DictationService dictationService;

    @GetMapping()
    public ResponseEntity<?> getDictationList() {
        DictationResponseDto dictationResponseDto = dictationService.getDictationList();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("받아쓰기 문장 조회에 성공하였습니다.",dictationResponseDto));
    }
}
