package com.finn.security;

import com.finn.exception.MyAccessDeniedException;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @description: 自定义权限管理器 鉴权
 * @author: Finn
 * @create: 2022/01/29 19:46
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private boolean isSupports = true;
    
    /* 
    * @Description: 鉴权流程
    * @Param: [authentication, object, collection]
    * @return: void 
    * @Author: Finn
    * @Date: 2022/01/29 19:52
    */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//        /*
//        * configAttributes: MySecurityMetadataSource提供请求的url所需的身份信息
//        * authentication: 在UserDetailsImpl中放入的用户role信息
//        */
//        // 该url不需要任何身份，直接放行
//        if(configAttributes == null) {
//            return;
//        }
//        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
//        while (iterator.hasNext()) {
//            // 需要的身份信息
//            ConfigAttribute c = iterator.next();
//            String needAuth = c.getAttribute();
//            // 获取用户权限
//            for(GrantedAuthority ga : authentication.getAuthorities()) {
//                if(needAuth.trim().equals(ga.getAuthority()))
//                    return;
//            }
//        }
//        throw new MyAccessDeniedException("没有权限访问！");
        // 获取用户权限列表
        List<String> permissionList = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        for (ConfigAttribute item : configAttributes) {
            if (permissionList.contains(item.getAttribute())) {
                return;
            }
        }
        throw new AccessDeniedException("没有操作权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return isSupports;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return isSupports;
    }
}
