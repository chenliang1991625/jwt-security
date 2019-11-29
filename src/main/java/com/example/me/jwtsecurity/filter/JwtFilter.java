package com.example.me.jwtsecurity.filter;

import com.example.me.jwtsecurity.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Spring为我们提供了 org.springframework.web.servlet.handler.HandlerInterceptorAdapter这个适配器，
 *  继承此类，可以非常方便的实现自己的拦截器。他有三个方法： 分别实现预处理、后处理（调用了Service并返回ModelAndView，
 * 但未进行页面渲 染）、返回处理（已经渲染了页面）
 * 在preHandle中，可以进行编码、安全控制等处理； 在postHandle中，
 * 有机会修改ModelAndView； 在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 * */
//创建拦截器类。创建 com.tensquare.user.filter.JwtFilter
@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        final String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization="+authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            // The part after "Bearer
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {//如果是管理员
                    request.setAttribute("admin_claims", claims);
                }
                if ("user".equals(claims.get("roles"))) {//如果是用户
                    request.setAttribute("user_claims", claims);
                }
            }
        }
        return true;
    }
}