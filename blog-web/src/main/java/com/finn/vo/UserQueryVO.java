package com.finn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "userQueryVO", description = "角色下拉菜单查询条件")
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页， 默认为1")
    private Integer current = 1;

    @ApiModelProperty(value = "当前页显示个数，默认为5")
    private Integer size = 5;

    @ApiModelProperty(value = "可以根据role_name来查询角色")
    private String roleName;

    @ApiModelProperty(value = "可以根据nickname来查询角色")
    private String nickname;

}
