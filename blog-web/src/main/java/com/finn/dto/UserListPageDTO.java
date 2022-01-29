package com.finn.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/*
 * @description: 用户列表
 * @author: Finn
 * @create: 2022-01-26-20-02
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserListPageDTO", description = "用户列表")
public class UserListPageDTO {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户角色")
    private String roleName;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("是否禁言")
    private Integer isSilence;

//    @ApiModelProperty("操作")
//    private String action;
}
