package com.finn.exception;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-19-21-52
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationException(String msg) {
        super(msg);
    }
}
