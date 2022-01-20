package com.finn.exception;



/*
 * @description:
 * @author: Finn
 * @create: 2022-01-19-21-48
 */
public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(String msg) {
        super(msg);
    }

    public UsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
