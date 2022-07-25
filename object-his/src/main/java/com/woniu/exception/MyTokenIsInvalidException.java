package com.woniu.exception;

import org.springframework.security.core.AuthenticationException;

public class MyTokenIsInvalidException extends AuthenticationException{

    public MyTokenIsInvalidException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyTokenIsInvalidException(String msg) {
        super(msg);
    }
}
