package com.finn.security.config;

import com.finn.security.AuthenticationTokenFilter;
import com.finn.security.MyFilterSecurityInterceptor;
import com.finn.security.UserDetailsServiceImpl;
import com.finn.security.handler.MyLogoutSuccessHandler;
import com.finn.security.handler.MyUsernamePasswordAuthenticationFilterHandler;
import com.finn.security.handler.MyAuthenticationFailureHandler;
import com.finn.security.handler.MyAuthenticationSuccessHandler;
import io.swagger.annotations.Api;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
    /*从 FilterSecurityInterceptor 类开始鉴权流程*/

    @Autowired
    private UserDetailsServiceImpl myUserDetailService;
//    @Autowired
//    private MyUsernamePasswordAuthenticationFilterHandler myUsernamePasswordAuthenticationFilterHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;


    /*
    * @Description:  配置http
    * @Param: [http]
    * @return: null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*替换密码校验过滤器*/
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);

        http.cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()    // 授权配置
            .requestMatchers(CorsUtils::isPreFlightRequest)
            .permitAll()
            .antMatchers("/session/invalid")
            .permitAll()       // 无需认证的请求路径
            .anyRequest()       // 任何请求
//            .authenticated()    //都需要身份认证
                .permitAll() // 不需要身份认证
            .and()
            /*登录和注销*/
            .formLogin().loginProcessingUrl("http://10.12.37.207:3000/login")
            .and()
            .logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler)
            .and()
            .sessionManagement() // 添加session管理器
            .invalidSessionUrl("http://10.12.37.207:3000/login") // Session失效后跳转到这个链接
            ;


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
