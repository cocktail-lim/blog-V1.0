package com.finn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @description: swagger2的配置类
 * @author: Finn
 * @create: 2022-01-13-16-23
 */
@Configuration
@EnableSwagger2
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true") // 控制@Configuration是否生效
public class Swagger2Conifg {

    @Value("${enable.swagger2}")
    private boolean enableSwagger;

    @Bean
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger) //上线后关闭 .enable(false)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.finn.controller"))
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Finn的博客文档")
                .version("1.0")
                .description("springboot 2 + vue 的博客系统")
                .build();
    }

}
