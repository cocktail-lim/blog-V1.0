package com.finn.security.handler;

import com.alibaba.fastjson.JSON;
import com.finn.enums.ResultEnums;
import com.finn.util.ResultUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @description: 用户认证失败处理器
 * @author: Finn
 * @create: 2022-01-18-16-33
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;");
        httpServletResponse.getWriter().write(
                JSON.toJSONString(ResultUtils.error().codeAndMessage(ResultEnums.LOGIN_FAILED))
        );
    }
}
