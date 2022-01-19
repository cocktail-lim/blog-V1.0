package com.finn.enums;

public enum ResultEnums {
    /*
    * 枚举类的对象
    * */
    GLOBAL_ERROR("101", "Sorry, 全局异常，系统繁忙。"),
    NOT_FOUND("404", "Sorry, 页面没有找到！"),
    SUCCESS("200", "Congratulations, 操作成功！"),
    ACCESS_DENIED("401", "Sorry to say, 身份验证失败！")
    ;
    /*
    * 申明枚举类的对象属性
    * */
    private String code;
    private String message;

    /*
    * 创建构造类
    * */
    ResultEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /*
    * 获取对象的code
    * */
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
