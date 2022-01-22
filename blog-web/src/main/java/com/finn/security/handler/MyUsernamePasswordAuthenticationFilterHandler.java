package com.finn.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finn.exception.AuthenticationException;
import com.finn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
 * @description:
 * @author: Finn
 * @create: 2022-01-16-16-09
 */

// 放入spring容器中，可以在该类上标明@component，或者在其他类中标识@Bean，并声明方法来得到该对象
public class MyUsernamePasswordAuthenticationFilterHandler extends UsernamePasswordAuthenticationFilter {

    @Autowired
    UserService userService;

    // 原来只能处理 form 数据; 重写方法，使其支持处理 json 数据
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication Method is not supported: " + request.getMethod());
        }

        // 读请求，解析出 username 和 password
        if(request.getContentType().equals("application/json;charset=UTF-8")
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            Map<String, String> authenticationBean = new HashMap<>(); //存入 username 和 password
            ObjectMapper objectMapper = new ObjectMapper();

            try(InputStream is = request.getInputStream()){
                authenticationBean = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(!authenticationBean.isEmpty()) {
                    // 获取账号密码
                    String username = authenticationBean.get("username");
                    String password = authenticationBean.get("password");

//                    // 打印到控制台
//                    System.out.println("username: " + username);
//                    System.out.println("password: " + password);

                    if (username == null) {
                        username = "";
                    }

                    if (password == null) {
                        password = "";
                    }

                    username = username.trim();

                    // 检查账号密码是否存在
                    // Principal: 获取用户身份信息，在未认证的情况下获取到的是用户名，在已认证的情况下获取到的是 UserDetails。
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
                    if(userService.checkLogin(username, password)) {
                        // 认证成功的user封装到userDetails里
                        this.setDetails(request, authenticationToken);
                    }

                    return this.getAuthenticationManager().authenticate(authenticationToken);
                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        } else {
            throw new AuthenticationException("不是json格式");
        }

        return this.attemptAuthentication(request, response);
    }
}
