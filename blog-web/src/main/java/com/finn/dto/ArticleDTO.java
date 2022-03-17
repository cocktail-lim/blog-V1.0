package com.finn.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/*
 * @description: 文章内容展示
 * @author: Finn
 * @create: 2022/02/05 21:39
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ArticleDTO", description = "展示页文章内容")
public class ArticleDTO {

    @ApiModelProperty("文章主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章内容")
    private String articleCover;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("标签列表")
    private List<TagDTO> tagList;

    @ApiModelProperty("浏览量")
    private Integer viewsCount;

    @ApiModelProperty("上一篇文章")
    private ArticlePaginationDTO preArticle;

    @ApiModelProperty("下一篇文章")
    private ArticlePaginationDTO nextArticle;

    @ApiModelProperty("推荐文章")
    private List<ArticleRecommendDTO> recommendArticleList;

    @ApiModelProperty("最新")
    private List<ArticleRecommendDTO> newestArticleList;
}
