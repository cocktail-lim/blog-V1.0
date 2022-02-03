package com.finn.dto;

import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "UserListPageDTO", description = "用户列表 用户信息")
public class UserListPageDTO {

    @ApiModelProperty("用户ID")
    @TableId(value = "user_id") //作为主键的属性不设置的话，mybatis plus查不到
    private Integer userId;

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

}
