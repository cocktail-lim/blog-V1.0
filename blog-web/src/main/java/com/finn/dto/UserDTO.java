package com.finn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/*
 * @description: User信息
 * @author: Finn
 * @create: 2022-01-29-11-33
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserDTO", description = "用户列表")
public class UserDTO {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("是否禁言")
    private int isSilence;
}