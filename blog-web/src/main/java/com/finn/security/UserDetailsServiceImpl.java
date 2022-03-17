package com.finn.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.finn.entity.User;
import com.finn.entity.UserAuth;
import com.finn.entity.UserInfo;
import com.finn.exception.MyRuntimeException;
import com.finn.mapper.RoleMapper;
import com.finn.mapper.UserAuthMapper;
import com.finn.mapper.UserInfoMapper;
import com.finn.service.*;
import com.finn.utils.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.finn.constant.RedisPrefixConst.*;
import static com.finn.enums.ZoneEnum.SHANGHAI;

/*
 * @description: 从数据库里查询到用户信息和权限信息并封装成UserDetails返回
 * @return: UserDetails的是实现类，MyUserDetails
 * @author: Finn
 * @create: 2022-01-16-11-16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisService redisService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new MyRuntimeException("用户名不能为空！");
        }
        // 查询账号是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getId, UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getPassword, UserAuth::getLoginType)
                .eq(UserAuth::getUsername, username));
        if (Objects.isNull(userAuth)) {
            throw new MyRuntimeException("用户名不存在!");
        }
        // 封装登录信息
        return convertUserDetail(userAuth, request);
    }

    /**
     * 封装用户登录信息
     *
     * @param user    用户账号
     * @param request 请求
     * @return 用户登录信息
     */
    public MyUserDetails convertUserDetail(UserAuth user, HttpServletRequest request) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String result = encoder.encode("123456");
//        System.out.println(result);
        // 查询账号信息
        UserInfo userInfo = userInfoMapper.selectById(user.getUserInfoId());
        // 查询账号角色
        List<String> roleList = roleMapper.listRolesByUserInfoId(userInfo.getId());
        // 查询账号点赞信息
        Set<Object> articleLikeSet = redisService.sMembers(ARTICLE_USER_LIKE + userInfo.getId());
        Set<Object> commentLikeSet = redisService.sMembers(COMMENT_USER_LIKE + userInfo.getId());
        Set<Object> talkLikeSet = redisService.sMembers(TALK_USER_LIKE + userInfo.getId());
        // 获取设备信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = IpUtils.getUserAgent(request);
        // 封装权限集合
        return MyUserDetails.builder()
                .id(user.getId())
                .loginType(user.getLoginType())
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password("{noop}" + user.getPassword())
                .email(userInfo.getEmail())
                .roleList(roleList)
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .talkLikeSet(talkLikeSet)
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isDisable(userInfo.getIsDisable())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .lastLoginTime(LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())))
                .build();
    }



//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        if(StringUtils.isBlank(username))
//            throw new MyRuntimeException("用户名为空！");
//
//        // 获取数据库里的user信息
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("username", username);
//        User user = userService.getOne(userQueryWrapper);
//        System.out.println(user);
//        if(user != null) {
//            // 把数据库里的信息拿出来封装成 UserDetails
//            MyUserDetails myUserDetails = new MyUserDetails();
////            myUserDetails.setUser(user);
//            myUserDetails.setUsername(user.getUsername());
//            myUserDetails.setPassword("{noop}" + user.getPassword()); // noop表示未加密状态
//
//            List<String> roles = userService.listUserRolesByUsername(username); //获取当前用户的角色集
//            SimpleGrantedAuthority authority;
//            Set<GrantedAuthority> authorities = new HashSet<>();
//            for (String role : roles) {
//                authority = new SimpleGrantedAuthority(role);
//                authorities.add(authority);
//            }
//
////            myUserDetails.setAuthorities(authorities);
//            return myUserDetails;
//        } else
//            throw new UsernameNotFoundException("用户不存在！");
//    }
}
