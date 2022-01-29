package com.finn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * @description: 用户信息的查询条件 [用户角色] [昵称]
 * @author: Finn
 * @create: 2022-01-26-20-07
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserQueryVO implements Serializable {

    // 可以根据role来查询角色数据
    private String roleName;
    // 也可以根据nickName来查询角色数据
    private String nickname;

}
