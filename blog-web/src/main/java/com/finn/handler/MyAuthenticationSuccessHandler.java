package com.finn.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        System.out.println("登录验证成功！");
//        httpServletResponse.getWriter()
//                .write(
//                        JSON.toJSONString(
//                                new Result<UserInfoDTO>(true, StatusConst.OK, "登录成功！", userLoginDTO)
//                        )
//                );
    }
}
