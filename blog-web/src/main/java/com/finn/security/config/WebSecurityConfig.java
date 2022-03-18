package com.finn.security.config;

import com.finn.security.*;
import com.finn.security.handler.*;
import io.swagger.annotations.Api;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private UserDetailsServiceImpl myUserDetailService;

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new MySecurityMetadataSource();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new MyAccessDecisionManager();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

//    /**
//     * 密码加密
//     *
//     * @return {@link PasswordEncoder} 加密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /*
    * @Description:  配置http
    * @Param: [http]
    * @return: null
    * @Author: Finn
    * @Date: 2022/1/18
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置登录注销路径
        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler);
        // 配置路由权限信息
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setSecurityMetadataSource(securityMetadataSource());
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        return fsi;
                    }
                })
                .anyRequest().permitAll()
                .and()
                .cors()
                .and()
                // 关闭跨站请求防护
                .csrf().disable().exceptionHandling()
                // 未登录处理
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                // 权限不足处理
                .accessDeniedHandler(myAccessDeniedHandler);
//                .and()
//                .sessionManagement()
//                .maximumSessions(20)
//                .sessionRegistry(sessionRegistry());
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
        auth.userDetailsService(myUserDetailService)
                ;
//                .passwordEncoder(passwordEncoder());
    }

//    /*
//     * @Description: 跨域过滤器
//     * @Param: []
//     * @return: 一个跨域过滤器
//     * @Author: Finn
//     * @Date: 2022/1/19
//     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8090", "http://localhost:8091"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//}

//    /*
//     * @Description: 跨域过滤器
//     * @Param: []
//     * @return: 一个跨域过滤器
//     * @Author: Finn
//     * @Date: 2022/1/19
//     */
//    @Bean
//        public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(source);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        /*替换密码校验过滤器*/
//        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//
//        http.cors()
//            .and()
//            .csrf()
//            .disable()
//            .authorizeRequests()    // 授权配置
//            .requestMatchers(CorsUtils::isPreFlightRequest)
//            .permitAll()
//            .antMatchers("/session/invalid")
//            .permitAll()       // 无需认证的请求路径
//            .anyRequest()       // 任何请求
////            .authenticated()    //都需要身份认证
//                .permitAll() // 不需要身份认证
//            .and()
//            /*登录和注销*/
//            .formLogin().loginProcessingUrl("http://10.12.37.207:3000/login")
//            .and()
//            .logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler)
//            .and()
//            .sessionManagement() // 添加session管理器
//            .invalidSessionUrl("http://10.12.37.207:3000/login") // Session失效后跳转到这个链接
//            ;
//
//
//    }


//
    /*
    * @Description:  把自己写的 filter 注册到 spring 容器
    * @Param: []
    * @return:  null
    * @Author: Finn
    * @Date: 2022/1/18
    */
//    @Bean
//    public MyUsernamePasswordAuthenticationFilterHandler myUsernamePasswordAuthenticationFilter() throws Exception {
////        MyUsernamePasswordAuthenticationFilterHandler myUsernamePasswordAuthenticationFilterHandler = new MyUsernamePasswordAuthenticationFilterHandler();
////        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler); // 验证成功
////        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationFailureHandler(myAuthenticationFailureHandler);  // 验证失败
//        myUsernamePasswordAuthenticationFilterHandler.setAuthenticationManager(authenticationManagerBean());
//        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
//        usernamePasswordAuthenticationFilter.setAuthenticationManager();
//        return myUsernamePasswordAuthenticationFilterHandler;
//    }
//


}
