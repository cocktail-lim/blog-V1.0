package com.finn.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @description: MyBatis Plus的配置文件
 * @author: Finn
 * @create: 2022-01-29-17-31
 */
@Configuration
public class MybatisPlusConfig {

    /*
    * @Description:  添加表插件
    * @Param: []
    * @return: com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
    * @Author: Finn
    * @Date: 2022/02/03 16:53
    */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
