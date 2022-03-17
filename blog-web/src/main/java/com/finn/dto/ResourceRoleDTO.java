package com.finn.dto;

import lombok.Data;

import java.util.List;

/*
 * @description: 资源角色
 * @author: Finn
 * @create: 2022/03/15 22:22
 */
@Data
public class ResourceRoleDTO {

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;

}
