package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @description: 返回给前端的后台用户登录信息
 * @author: Finn
 * @create: 2022-01-21-15-28
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "UserLoginDTO", description = "用户登录信息")
public class UserLoginDTO {

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty("用户表主键id")
    private Integer userId;

    @ApiModelProperty("用户信息Id")
    private Integer userInfoId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("个人简介")
    private String intro;

    @ApiModelProperty("是否禁言 0：不禁言 1：禁言")
    private Boolean isSilence;

    @ApiModelProperty("用户角色")
    private List<String> roleList;

//    @ApiModelProperty("token")
//    private Token token;
}
