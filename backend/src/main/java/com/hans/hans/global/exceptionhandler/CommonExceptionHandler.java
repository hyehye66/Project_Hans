package com.hans.hans.global.exceptionhandler;

import com.hans.hans.global.exception.*;
import com.hans.hans.global.response.CommonResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(ExistNicknameException.class)
    public ResponseEntity<CommonResponse> handleExistNicknameException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistMemberException.class)
    public ResponseEntity<CommonResponse> handleNoExistMemberException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistRoomException.class)
    public ResponseEntity<CommonResponse> handleNoExistRoomException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(BindingResult bindingResult){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.createFail(bindingResult));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<CommonResponse> handleJwtException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistRankingListSearchByNicknameException.class)
    public ResponseEntity<CommonResponse> handleNoExistRankingListSearchByNicknameException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistRoomSearchByTitleException.class)
    public ResponseEntity<CommonResponse> handleNoExistRoomSearchByTitleException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistRoomSearchByNicknameException.class)
    public ResponseEntity<CommonResponse> handleNoExistRoomSearchByNicknameException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<CommonResponse> handleNotLoggedInException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.createError(e.getMessage()));
    }
}
