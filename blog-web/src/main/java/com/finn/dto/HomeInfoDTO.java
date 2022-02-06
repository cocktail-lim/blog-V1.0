package com.finn.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/*
 * @description: 首页信息
 * @author: Finn
 * @create: 2022/02/06 20:38
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
public class HomeInfoDTO {

    /**
     * 文章数量
     */
    private Integer articleCount;

    /**
     * 分类数量
     */
    private Integer categoryCount;

    /**
     * 标签数量
     */
    private Integer tagCount;

    /**
     * 访问量
     */
    private String viewsCount;

//    /**
//     * 网站配置
//     */
//    private WebsiteConfigVO websiteConfig;

    /**
     * 页面列表
     */
    private List<PageDTO> pageList;
}
