package com.hans.hans.global.exception;

import io.openvidu.java.client.OpenViduHttpException;

public class SessionCreateException extends RuntimeException {
    public SessionCreateException(String message){
        super(message);
    }
}
