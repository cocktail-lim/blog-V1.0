package com.finn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_role")
@ApiModel(value = "Role", description = "用户角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户表主键")
    private Integer id;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("是否禁用 0否 1是")
    private String isDisable;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
