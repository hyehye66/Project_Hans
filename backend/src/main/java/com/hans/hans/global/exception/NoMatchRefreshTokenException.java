package com.hans.hans.global.exception;

public class NoMatchRefreshTokenException extends RuntimeException{
    public NoMatchRefreshTokenException(String message){
        super(message);
    }
}
