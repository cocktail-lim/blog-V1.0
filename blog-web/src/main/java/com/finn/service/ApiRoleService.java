package com.finn.service;

import com.finn.entity.ApiRole;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: 角色对于url的权限
 * @author: Finn
 * @create: 2022/01/30 18:26
 */
public interface ApiRoleService {

    /*
    * @Description: 通过请求api的url来获取拥有访问权限的role
    * @Param: []
    * @return: java.util.List<com.finn.entity.UrlRole>
    * @Author: Finn
    * @Date: 2022/01/30 18:37
    */
    List<ApiRole> getApiRole();
}
