package com.woniu.exception;

import org.springframework.security.core.AuthenticationException;

public class MyTokenIsNullException extends AuthenticationException{

    public MyTokenIsNullException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyTokenIsNullException(String msg) {
        super(msg);
    }
}
