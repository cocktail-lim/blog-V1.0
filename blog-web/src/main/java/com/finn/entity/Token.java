package com.finn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/*
 * @description: token entity
 * @author: Finn
 * @create: 2022-01-24-17-19
 */
@Setter
@Getter
@ToString
@ApiModel(value = "Token", description = "Token")
public class Token {

    @ApiModelProperty("Token ID")
    private Integer id;

    @ApiModelProperty("用户角色")
    private Set<String> role;

    @ApiModelProperty("上次登录时间")
    private Date lastLogin;

}
