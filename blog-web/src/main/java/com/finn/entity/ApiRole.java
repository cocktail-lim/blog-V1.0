package com.finn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/*
 * @description: Url对应的Role
 * @author: Finn
 * @create: 2022/01/30 18:29
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ApiRole", description = "请求接口的role权限")
public class ApiRole {

    @ApiModelProperty(value = "接口ID")
    private Integer apiId;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "请求方法")
    private String method;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;
}

