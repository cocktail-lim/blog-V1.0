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
 * @since 2022-02-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("//分类id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("//分类名称")
    private String categoryName;

    @ApiModelProperty("//创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("//更新时间")
    private LocalDateTime updateTime;


}
