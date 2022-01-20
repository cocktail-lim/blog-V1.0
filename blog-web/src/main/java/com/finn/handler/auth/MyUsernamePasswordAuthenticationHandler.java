package com.finn.handler.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
public class MyUsernamePasswordAuthenticationHandler extends UsernamePasswordAuthenticationFilter {

    // 原来只能处理 form 数据
    // 重写方法，使其支持处理 json 数据
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Map<String, String> map = new HashMap<>(); //存入 username 和 password
        ObjectMapper objectMapper = new ObjectMapper();
        if(request.getContentType().equals("application/json;charset=UTF-8")
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            if(!request.getMethod().equals("POST")) {
                throw new AuthenticationServiceException(
                        "Authentication Method is not supported: " + request.getMethod());
            }

            try(InputStream is = request.getInputStream()){
                map = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!map.isEmpty()) {
                String username = map.get("username");
                String password = map.get("password");

                // 打印到控制台
                System.out.println("username: " + username);
                System.out.println("password: " + password);
//                try {
//                    objectMapper.writeValue(System.out, "username: " + username + "++++" + "password: " + password);
//                } catch (IOException e) {
//                    e.printStackTrace() ;
//                }

                if (username == null) {
                    username = "";
                }

                if (password == null) {
                    password = "";
                }
                
                username = username.trim();
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                this.setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }

        return this.attemptAuthentication(request, response);
    }
}
