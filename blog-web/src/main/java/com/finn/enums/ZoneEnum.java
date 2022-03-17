package com.finn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @description: 时区枚举
 * @author: Finn
 * @create: 2022/03/16 00:01
 */
@Getter
@AllArgsConstructor
public enum ZoneEnum {
    /**
     * 上海
     */
    SHANGHAI("Asia/Shanghai", "中国上海");

    /**
     * 时区
     */
    private final String zone;

    /**
     * 描述
     */
    private final String desc;
}
