package com.hans.hans.global.exceptionhandler;

import com.hans.hans.global.exception.ExistNicknameException;
import com.hans.hans.global.exception.NoExistMemberException;
import com.hans.hans.global.exception.NoExistRoomException;
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

    // 중복 닉네임 에러 Handler
    @ExceptionHandler(ExistNicknameException.class)
    public ResponseEntity<CommonResponse> handleExistNicknameException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(CommonResponse.createError(e.getMessage()));
    }

    // 존재하는 회원이 없는 에러 Handler
    @ExceptionHandler(NoExistMemberException.class)
    public ResponseEntity<CommonResponse> handleNoExistMemberException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.createError(e.getMessage()));
    }

    @ExceptionHandler(NoExistRoomException.class)
    public ResponseEntity<CommonResponse> handleNoExistRoomException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.createError(e.getMessage()));
    }

    // validation 유효성 실패 에러 Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(BindingResult bindingResult){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.createFail(bindingResult));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<CommonResponse> handleJwtException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.createError(e.getMessage()));
    }


}
