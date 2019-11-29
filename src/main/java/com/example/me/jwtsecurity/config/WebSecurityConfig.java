package com.example.me.jwtsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 * authenticated()要求认证后才能访问。
 * 如果用户没有认证的话，Spring Security的Filter将会捕获该请求，并将用户重定向到应用的登录页面。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //authorizeRequests:声明配置是权限配置
        //antMatchers：路径
        //permitAll：任何权限都可以访问，不需要身份认证
        //anyRequest：任何请求
        //authenticated：认证后才能访问
        //and().csrf().disable()：固定写法，表示csrf拦截失效

           http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}


   /*
   权限设置说明:
        access(String) 如果给定的SpEL表达式计算结果为true，就允许访问

        anonymous() 允许匿名用户访问

        authenticated() 允许认证的用户进行访问

        denyAll() 无条件拒绝所有访问

        fullyAuthenticated() 如果用户是完整认证的话（不是通过Remember-me功能认证的），就允许访问

        hasAuthority(String) 如果用户具备给定权限的话就允许访问

        hasAnyAuthority(String…) 如果用户具备给定权限中的某一个的话，就允许访问

        hasRole(String) 如果用户具备给定角色(用户组)的话,就允许访问

        hasAnyRole(String…) 如果用户具有给定角色(用户组)中的一个的话,允许访问

        hasIpAddress(String) 如果请求来自给定ip地址的话,就允许访问

        not() 对其他访问结果求反

        permitAll() 所有权限无条件允许访问

        rememberMe() 如果用户是通过Remember-me功能认证的，就允许访问*/