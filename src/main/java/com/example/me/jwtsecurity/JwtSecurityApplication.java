package com.example.me.jwtsecurity;

import com.example.me.jwtsecurity.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//扫描的mapper
@MapperScan("com.example.me.jwtsecurity.mapper")
/*2，禁用安全设置或者设置对应的用户和密码
可以在application.properteis中配置对应的用户和密码
也可以设置对应的用户名和密码
spring.security.user.name=user1
spring.security.user.password=password1
通过在启动main类上禁用 @SpringBootApplication/*(exclude = {SecurityAutoConfiguration.class})
原文链接：https://blog.csdn.net/russle/article/details/82454921*/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
/*@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {com.example.me.jwtsecurity.mapper.UserMapper.class})})*/
@ImportResource(locations = {"classpath: **/*.xml", "classpath: **/*.yml", "classpath: **/*.properties"})
public class JwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }

    /*注入spring-boot-starter-security加密解密的bean*/
    @Bean
    public BCryptPasswordEncoder encoding() {
        return new BCryptPasswordEncoder();
    }

    /*注入生成id的Bean*/
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
 /*jwt认证工具注入*//*
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }*/
}
