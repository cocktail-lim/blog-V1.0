package com.finn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/*
 * @description: RoleSelectListDTO
 * @author: Finn
 * @create: 2022-01-27-18-17
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
@ApiModel(value = "RoleSelectListDTO", description = "角色列表")
public class RoleSelectListDTO {

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色用户个数")
    private Integer roleNums;
}
