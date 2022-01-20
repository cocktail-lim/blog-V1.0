package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.finn.entity.MyUserDetails;
import com.finn.entity.User;
import com.finn.exception.MyRuntimeException;
import com.finn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-16-11-16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username))
            throw new MyRuntimeException("用户名为空！");
        // 获取数据库里的username
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userService.getOne(userQueryWrapper);
        if(user != null) {
//            System.out.println("匹配到数据库中的：" + user.toString());
            // 用数据库里的信息来验证登录即可
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
//            throw new MyRuntimeException(ResultInfo.ACCESS_DENIED);
    }
}
