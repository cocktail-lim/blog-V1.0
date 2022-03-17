package com.finn.security;

import com.finn.dto.ResourceRoleDTO;
import com.finn.entity.ApiRole;
import com.finn.service.ApiRoleService;
import com.finn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*
 * @description: 自定义配置
 *               从数据库tb_api_role里读取请求的api所需要的身份信息
 *               返回给MyAccessDecisionManager，让其鉴权
 * @author: Finn
 * @create: 2022/01/29 18:51
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private boolean isSupports = true;

    @Autowired
    private RoleService roleService;

    private List<ResourceRoleDTO>  resourceRoleDTOList;
    //    @Autowired
//    private ApiRoleService apiRoleService;
    /* <url, 所需的 role 集合> */
    private Map<List<String>, Collection<ConfigAttribute>> map;

    /*
    * @Description: 判定用户请求的api是否在权限表中
    *               url如果在权限表中，则返回给权限管理类的decide方法，用来判定用户是否有此权限
    *               url如果不在权限表中则放行 ——> ？跳转到/login？
    * @Param: [object]
    * @return: java.util.Collection<org.springframework.security.access.ConfigAttribute> 
    * @Author: Finn
    * @Date: 2022/01/29 19:05
    */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 修改接口角色关系后重新加载
        if (CollectionUtils.isEmpty(resourceRoleDTOList)) {
            this.initLoadDatabase();
        }
        FilterInvocation fi = (FilterInvocation) object;
        // 获取用户请求方式
        String method = fi.getRequest().getMethod();
        // 获取用户请求Url
        String url = fi.getRequest().getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 获取接口角色信息，若为匿名接口则放行，若无对应角色则禁止
        for (ResourceRoleDTO resourceRoleDTO : resourceRoleDTOList) {
            if (antPathMatcher.match(resourceRoleDTO.getUrl(), url) && resourceRoleDTO.getRequestMethod().equals(method)) {
                List<String> roleList = resourceRoleDTO.getRoleList();
                if (CollectionUtils.isEmpty(roleList)) {
                    return SecurityConfig.createList("disable");
                }
                return SecurityConfig.createList(roleList.toArray(new String[]{}));
            }
        }
        return null;
//        if(map == null) {
//            // 如果map为空，则加载数据库里信息
//            initLoadDatabase();
//        }
//        HttpServletRequest request = ((FilterInvocation) object).getRequest();
//        String requestUrl = request.getRequestURI();
//        String requestMethod = request.getMethod();
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        // 查看数据库里有没有该api的权限信息
//        for(Map.Entry<List<String>, Collection<ConfigAttribute>> entry : map.entrySet()) {
//            // 查到，返回所需权限
//            if(antPathMatcher.match(entry.getKey().get(0), requestUrl) && requestMethod.equals(entry.getKey().get(1)))
//                return entry.getValue();
//        }
//        // 未查到，返回null
//        return null;
    }

    /*
    * @Description: 读取数据库里tb_api_role来获取url所需的role信息
    * @Param: []
    * @return: void
    * @Author: Finn
    * @Date: 2022/01/30 16:38
    */
    public void initLoadDatabase(){
        resourceRoleDTOList = roleService.listResourceRoles();
//        // 初始化
//        this.map = new HashMap<>();
//        Collection<ConfigAttribute> configAttributes;
//        List<String> urlAndMethod;
//        ConfigAttribute cfg;
//
//        // 获取Api对应的权限
//        List<ResourceRoleDTO> resourceRoleDTOList = roleService.listResourceRoles();
//        for(ResourceRoleDTO resourceRoleDTO : resourceRoleDTOList) {
//            configAttributes = new ArrayList<>();
//            urlAndMethod = new ArrayList<>();
//            List<String> roleList = resourceRoleDTO.getRoleList();
//            if(roleList != null){
//                cfg = new SecurityConfig(resourceRoleDTO.getRoleName());
//                configAttributes.add(cfg);
//            } else
//                configAttributes.add(null);
//            urlAndMethod.add(resourceRoleDTO.getUrl());
//            urlAndMethod.add(resourceRoleDTO.getRequestMethod());
//            map.put(urlAndMethod, configAttributes);
//        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return isSupports;
    }
}
