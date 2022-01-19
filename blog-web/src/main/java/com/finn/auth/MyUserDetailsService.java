package com.finn.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finn.entity.User;
import com.finn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-16-11-16
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取数据库里的username
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userService.getOne(userQueryWrapper);
        if(user != null) {
            System.out.println("匹配到数据库中的：" + user.toString());
            // 用数据库里的信息来验证登录即可
            MyUserDetails myUserDetails = new MyUserDetails();
            myUserDetails.setUser(user);
            myUserDetails.setUsername(user.getUsername());
            myUserDetails.setPassword("{noop}" + user.getPassword()); // noop表示未加密状态
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("TEST");
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(authority);
            myUserDetails.setAuthorities(authorities);
            return myUserDetails;
        } else
            throw new UsernameNotFoundException("ACCESS_DENIED!");
//            throw new MyRuntimeException(ResultInfo.ACCESS_DENIED);
    }

}
