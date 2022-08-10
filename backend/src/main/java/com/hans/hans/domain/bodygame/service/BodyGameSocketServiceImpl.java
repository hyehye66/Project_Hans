package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.BodyGameProblemResponseDto;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.word.repository.WordRepository;
import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BodyGameSocketServiceImpl implements BodyGameSocketService {

    private final BodyGameRoomService bodyGameRoomService;
    private final WordRepository wordRepository;


    @Override
    public BodyGameStartResponseDto initGame(long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto){
        bodyGameRoomService.createBodyGameRoom(roomSequence,bodyGameStartRequestDto);
        BodyGameStartResponseDto bodyGameStartResponseDto = new BodyGameStartResponseDto("ready");
        return bodyGameStartResponseDto;
    }

    @Override
    public BodyGameProblemResponseDto getProblem(Long wordSequence){
        Word word = wordRepository.findByWordSequence(wordSequence);
        BodyGameProblemResponseDto bodyGameProblemResponseDto = new BodyGameProblemResponseDto(word);
        return bodyGameProblemResponseDto;
    }

}
