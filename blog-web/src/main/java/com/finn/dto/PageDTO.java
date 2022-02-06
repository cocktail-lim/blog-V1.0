package com.finn.dto;

import lombok.*;

/*
 * @description: show page 页面DTO
 * @author: Finn
 * @create: 2022/02/06 19:46
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    /*页面ID*/
    private Integer pageId;

    /*页面名字*/
    private String pageName;

    /*描述*/
    private String pageDescription;

    /*页面封面*/
    private String pageCover;

}
