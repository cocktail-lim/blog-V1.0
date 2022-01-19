package com.finn.exception;

import com.finn.enums.ResultEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-10-14-11
 */
@Getter
@Setter
@AllArgsConstructor

public class MyRuntimeException extends RuntimeException {

    private String code;
    private String message;

    public MyRuntimeException(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.message = resultEnums.getMessage();
    }

}
