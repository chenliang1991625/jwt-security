package com.example.me.jwtsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
//扫描的mapper
@MapperScan("com.example.me.jwtsecurity.mapper")
@SpringBootApplication/*(scanBasePackages = {"com.example.me"})
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {com.example.me.jwtsecurity.mapper.UserMapper.class})})*/
@ImportResource(locations = {"classpath: **/*.xml","classpath: **/*.yml","classpath: **/*.properties"})
public class JwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }

}
