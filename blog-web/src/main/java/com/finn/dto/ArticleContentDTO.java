package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/*
 * @description: 文章内容展示
 * @author: Finn
 * @create: 2022/02/05 21:39
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ArticleContentDTO", description = "展示页文章内容")
public class ArticleContentDTO {

    @ApiModelProperty("文章主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("标签Id")
    private List<String> tagList;
}
