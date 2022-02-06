package com.finn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import com.finn.dto.MenuDTO;
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
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_menu")
@ApiModel(value = "Menu", description = "后台管理左侧菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "//后台菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "//菜单列表名字")
    private String menuName;

    @ApiModelProperty(value = "//菜单的url")
    private String menuUrl;

    @ApiModelProperty(value = "//菜单的父ID")
    private Integer parentId;

    @ApiModelProperty(value = "//菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "//描述")
    private String description;

    @ApiModelProperty(value = "//菜单图标")
    private String menuIcon;
}
