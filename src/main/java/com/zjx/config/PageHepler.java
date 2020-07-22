package com.zjx.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHepler {
    //多数据这不起作用
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }




}
