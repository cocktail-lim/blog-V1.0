package com.finn.exception;

import com.finn.util.ResultUtils;
import com.finn.enums.ResultEnums;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @description: 全局异常处理
 * @author: Finn
 * @create: 2022-01-10-11-19
 */
@ControllerAdvice
public class GlobalException {

    // 捕获异常，并做处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultUtils exception(Exception e){
        e.printStackTrace();
        return ResultUtils.error().codeAndMessage(ResultEnums.GLOBAL_ERROR);
    }

    // 捕获自定义的异常，并做处理
    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public ResultUtils myRuntimeException(MyRuntimeException e){
        e.printStackTrace();
        return ResultUtils.error().codeAndMessage(e.getCode(), e.getMessage());
    }

}
