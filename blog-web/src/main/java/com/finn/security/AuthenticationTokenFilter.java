package com.finn.security;

import com.finn.entity.Token;
import com.finn.exception.UsernameNotFoundException;
import com.finn.service.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/*
 * @description: 认证用户Token信息
 * @author: Finn
 * @create: 2022/01/30 23:53
 */
//@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    
//    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        // 如果已认证
        if(context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            System.out.println("已认证");
        }
        // 否则认证 token 信息
        else {
            // 获取所有参数
            Map<String, String[]> params = httpServletRequest.getParameterMap();
            // 如果有 token 信息
            if(params.containsKey("token")) {
                // 获取user的token
                String requestToken = params.get("token")[0];
                if (requestToken != null) {
                    Token userToken = tokenUtils.parseToken(requestToken);
                    String username = userToken.getUsername();
                    Set<String> role = userToken.getRole();
                    Date lastLogin = userToken.getLastLogin();
                    Date nowLogin = new Date();
                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);// 根据用户名获取用户对象

                        if (userDetails != null) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                            // 设置为已登录
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else
                            throw new UsernameNotFoundException("Token认证时，未发现该用户！");
                    }
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
