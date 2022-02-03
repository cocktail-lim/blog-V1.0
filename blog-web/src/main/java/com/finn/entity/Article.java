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
 * 新增文章
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_article")
@ApiModel(value = "Article对象", description = "文章列表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章主键id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章封面")
    private String articleCover;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    @ApiModelProperty("是否草稿 0：不是草稿 1:草稿")
    private Boolean isDraft;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;


}
