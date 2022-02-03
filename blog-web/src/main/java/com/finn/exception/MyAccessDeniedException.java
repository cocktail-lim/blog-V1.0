package com.finn.exception;

import org.springframework.security.access.AccessDeniedException;

/*
 * @description: 没有访问权限抛出异常
 * @author: Finn
 * @create: 2022/01/30 15:01
 */
public class MyAccessDeniedException extends AccessDeniedException {
    public MyAccessDeniedException(String msg) {
        super(msg);
    }

    public MyAccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
}
