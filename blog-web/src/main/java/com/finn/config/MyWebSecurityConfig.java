package com.finn.config;

import com.finn.auth.MyUserDetailsService;
import com.finn.handler.MyUsernamePasswordAuthenticationHandler;
import com.finn.handler.MyAuthenticationFailureHandler;
import com.finn.handler.MyAuthenticationSuccessHandler;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * @description: Spring Security 配置类
 * @author: Finn
 * @create: 2022-01-16-14-47
 */

//throw new IllegalStateException(ObjectPostProcessor.class.getName() + " is a required bean. Ensure you have used @EnableWebSecurity and @Configuration");
@EnableWebSecurity
@Configuration
@Api(value = "Spring Security配置类")
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 从 FilterSecurityInterceptor 类开始鉴权流程

    @Autowired
    private MyUserDetailsService myUserDetailService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /*
    * @Description:  配置http
    * @Param: [http]
    * @return: null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginProcessingUrl("/login")  // 指定到登陆界面
//                .successHandler() // 成功后处理办法
//                .failureHandler() // 失败后处理办法
        ;
        http.csrf().disable().exceptionHandling(); // @EnableWebSecurity 会使csrf保护生效
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 替换spring security的 filter
    }

    /*
    * @Description: 配置AuthenticationManagerBuilder
    * @Param: [auth]
    * @return: null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    /*
    * @Description:  把自己写的 filter 注册到 spring 容器
    * @Param: []
    * @return:  null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Bean
    public MyUsernamePasswordAuthenticationHandler myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationHandler myUsernamePasswordAuthenticationHandler = new MyUsernamePasswordAuthenticationHandler();
        myUsernamePasswordAuthenticationHandler.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler); // 验证成功
        myUsernamePasswordAuthenticationHandler.setAuthenticationFailureHandler(myAuthenticationFailureHandler);  // 验证失败
        myUsernamePasswordAuthenticationHandler.setAuthenticationManager(authenticationManagerBean());
        return myUsernamePasswordAuthenticationHandler;
    }


}
