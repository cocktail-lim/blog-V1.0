package com.finn.security.handler;

import com.alibaba.fastjson.JSON;
import com.finn.dto.UserLoginDTO;
import com.finn.security.MyUserDetails;
import com.finn.enums.ResultEnums;
import com.finn.utils.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/*
 * @description: 用户认证成功处理器
 * @author: Finn
 * @create: 2022-01-18-16-11
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    /*
    * @Description: 给前端返回数据
    * @Param: [httpServletRequest, httpServletResponse, authentication]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 设置返回头
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        // 从已认证的Authentication中获得UserDetails
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        UserLoginDTO user = new UserLoginDTO();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Set<String> roles = new HashSet<>();

        for(GrantedAuthority grantedAuthority : authorities) {
            roles.add(grantedAuthority.getAuthority());
        }

        user.setUserId(userDetails.getUser().getUserId())
                .setUsername(userDetails.getUser().getUsername())
                .setNickname(userDetails.getUser().getNickname())
                .setAvatar(userDetails.getUser().getAvatar())
                .setIntro(userDetails.getUser().getIntro())
                .setIsSilence(userDetails.getUser().getIsSilence());

        httpServletResponse.getWriter().write(
                JSON.toJSONString(Result.success().codeAndMessage(ResultEnums.LOGIN_SUCCESS).data("userInfo", user))
        );
    }
}
