package com.finn.handler.auth;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finn.entity.MyUserDetails;
import com.finn.entity.User;
import com.finn.enums.ResultEnums;
import com.finn.util.ResultUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        Object principal = authentication.getPrincipal();
        httpServletResponse.setContentType("application/json;");
        User user;
        if(principal instanceof UserDetails) {
            MyUserDetails myUserDetails = (MyUserDetails) principal;
            user = myUserDetails.getUser();
            Collection<? extends GrantedAuthority> authorities = myUserDetails.getAuthorities();
            List<String> roles = new ArrayList<>();
            for(GrantedAuthority grantedAuthority : authorities) {
                roles.add(grantedAuthority.getAuthority());
            }
            httpServletResponse.getWriter().write(
                    JSON.toJSONString(ResultUtils.success().codeAndMessage(ResultEnums.LOGIN_SUCCESS).data("user", user))
            );
        }
//        httpServletResponse.getWriter()
//                .write(
//                        JSON.toJSONString(
//                                new Result<UserInfoDTO>(true, StatusConst.OK, "登录成功！", userLoginDTO)
//                        )
//                );
    }
}
