package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/*
 * @description: Admin System文章列表
 * @author: Finn
 * @create: 2022/02/04 11:48
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "Admin ArticleListPageDTO", description = "后台文章列表")
public class ArticleListPageDTO {

    @ApiModelProperty("文章主键id")
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("分类名")
    private Integer categoryId;

    @ApiModelProperty("是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    @ApiModelProperty("是否草稿 0：不是草稿 1:草稿")
    private Boolean isDraft;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("标签Id")
    private List<TagDTO> tagDTOList;
}
