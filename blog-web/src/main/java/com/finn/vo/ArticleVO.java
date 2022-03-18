package com.finn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/*
 * @description:
 * @author: Finn
 * @create: 2022/02/03 20:17
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "文章")
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer articleId;

    @NotBlank(message = "文章标题不能为空")
    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章封面")
    private String articleCover;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("tag id list")
    private List<Integer> tagList;

    @ApiModelProperty("是否置顶 0：不置顶 1：置顶")
    private Integer isTop;

    @ApiModelProperty("是否草稿 0：不是草稿 1:草稿")
    private Integer isDraft;

}
