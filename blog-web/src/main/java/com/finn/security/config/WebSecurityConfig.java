package com.finn.security.config;

import com.finn.security.UserDetailsServiceImpl;
import com.finn.security.handler.MyUsernamePasswordAuthenticationFilterHandler;
import com.finn.security.handler.MyAuthenticationFailureHandler;
import com.finn.security.handler.MyAuthenticationSuccessHandler;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * @description: Spring Security 配置类
 * @author: Finn
 * @create: 2022-01-16-14-47
 */

//throw new IllegalStateException(ObjectPostProcessor.class.getName() + " is a required bean. Ensure you have used @EnableWebSecurity and @Configuration");
@EnableWebSecurity
@Configuration
@Api(value = "Spring Security配置类")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 从 FilterSecurityInterceptor 类开始鉴权流程

    @Autowired
    private UserDetailsServiceImpl myUserDetailService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /*
    * @Description:  配置http，这里开启了跨域请求
    * @Param: [http]
    * @return: null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/login");  // 指定到登陆界面
        http.cors()
                .and()
                .authorizeRequests()//处理跨域请求中的Preflight请求
                .antMatchers("/api/admin/getMenus").permitAll()
                .antMatchers("/api/admin/userList/getRoleSelectList").permitAll()
                .antMatchers("/api/admin/userList/getUserList").permitAll()
                .antMatchers("/api/admin/userList/getUserListTest").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                .anyRequest()
                .authenticated(); // 需要认证
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
    public MyUsernamePasswordAuthenticationFilterHandler myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilterHandler myUsernamePasswordAuthenticationFilterHandler = new MyUsernamePasswordAuthenticationFilterHandler();
        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler); // 验证成功
        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationFailureHandler(myAuthenticationFailureHandler);  // 验证失败
        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationManager(authenticationManagerBean());
        return myUsernamePasswordAuthenticationFilterHandler;
    }

    /*
    * @Description: 跨域过滤器
    * @Param: []
    * @return: 一个跨域过滤器
    * @Author: Finn
    * @Date: 2022/1/19
    */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
