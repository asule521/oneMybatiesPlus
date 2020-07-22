package com.zjx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j(topic = "sys-user")
public class MybatiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatiesApplication.class,args);
        log.info("启动成功");
    }
}
