package com.finn.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.finn.entity.User;
import com.finn.exception.MyRuntimeException;
import com.finn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @description: 从数据库里查询到用户信息和权限信息并封装成UserDetails返回
 * @return: UserDetails的是实现类，MyUserDetails
 * @author: Finn
 * @create: 2022-01-16-11-16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(StringUtils.isBlank(username))
            throw new MyRuntimeException("用户名为空！");

        // 获取数据库里的user信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userService.getOne(userQueryWrapper);
        System.out.println(user);
        if(user != null) {
            // 把数据库里的信息拿出来封装成 UserDetails
            MyUserDetails myUserDetails = new MyUserDetails();
            myUserDetails.setUser(user);
            myUserDetails.setUsername(user.getUsername());
            myUserDetails.setPassword("{noop}" + user.getPassword()); // noop表示未加密状态

            List<String> roles = userService.listUserRolesByUsername(username); //获取当前用户的角色集
            SimpleGrantedAuthority authority;
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (String role : roles) {
                authority = new SimpleGrantedAuthority(role);
                authorities.add(authority);
            }

            myUserDetails.setAuthorities(authorities);
            return myUserDetails;
        } else
            throw new UsernameNotFoundException("用户不存在！");
    }
}
