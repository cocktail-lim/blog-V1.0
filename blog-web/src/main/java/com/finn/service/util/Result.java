package com.finn.service.util;

import com.finn.enums.ResultEnums;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/*
 * @description: 公共返回类
 * @author: Finn
 * @create: 2022-01-09-22-15
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Result {
    /*
    * 请求api时，
    * 返回“状态成功”或者“失败”
    * */
    private boolean status;

    /*
    * 返回状态码
    * */
    private String code;

    /*
    * 返回message
    * */
    private String message;

    /*
    * 返回数据
    * */
    private Map<String, Object> data = new HashMap<>();

    public Result() {
        super();
    }

    public static Result success(){
        Result result = new Result();
        result.setStatus(true);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(false);
        return result;
    }

    public Result data(String key, Object value){
//        Map<String, Object> data = new HashMap<>();
        data.put(key, value);
        this.setData(data);
        return this;
    }

    public Result codeAndMessage(String code, String message){
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public Result codeAndMessage(ResultEnums resultEnums){
        this.setCode(resultEnums.getCode());
        this.setMessage(resultEnums.getMessage());
        return this;
    }

}
