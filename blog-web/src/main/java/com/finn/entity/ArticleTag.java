package com.finn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString
@Builder
@Accessors(chain = true)
@TableName("tb_article_tag")
@ApiModel(value = "ArticleTag对象", description = "")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("article 和 tag的关联 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("//文章id")
    private Integer articleId;

    @ApiModelProperty("//标签id")
    private Integer tagId;


}
