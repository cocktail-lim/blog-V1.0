package com.finn.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * @description: 后台文章列表查询条件
 * @author: Finn
 * @create: 2022/02/04 14:38
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@ApiModel(value = "ArticleListVO", description = "后台文章列表查询条件")
public class ArticleListVO {

    @ApiModelProperty(value = "当前页， 默认为1")
    private Integer current = 1;

    @ApiModelProperty(value = "当前页显示个数，默认为10")
    private Integer size = 10;

    @ApiModelProperty(value = "可以根据文章标题来模糊查询")
    private String articleTitle;

//    @ApiModelProperty(value = "是否是草稿")
//    private boolean isDraft;
}
