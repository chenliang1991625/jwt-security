package com.example.me.jwtsecurity.config;

import com.example.me.jwtsecurity.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//配置拦截器类,创建com.tensquare.user.ApplicationConfig
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter).addPathPatterns("/**")
                                          .excludePathPatterns("/**/login")
                                          .excludePathPatterns("/");
    }
}