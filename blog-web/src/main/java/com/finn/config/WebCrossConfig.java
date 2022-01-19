package com.finn.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @description: Web Cross Config
 * @author: Finn
 * @create: 2022-01-16-15-06
 */
@Configuration
public class WebCrossConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .maxAge(6000);
    }

}
