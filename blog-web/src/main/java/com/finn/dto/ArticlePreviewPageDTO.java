package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/*
 * @description: 展示页文章封面
 * @author: Finn
 * @create: 2022/02/05 20:51
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ArticlePreviewPageDTO", description = "展示页文章列表")
public class ArticlePreviewPageDTO {

    @ApiModelProperty("文章主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章封面")
    private String articleCover;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("文章预览内容")
    private String articlePreviewContent;

    @ApiModelProperty("是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    @ApiModelProperty("发表时间")
    private Date createTime;

    @ApiModelProperty("标签Id")
    private List<TagDTO> tagList;
}
