package com.example.me.jwtsecurity.controller;

import com.example.me.jwtsecurity.service.UserService;
import com.example.me.jwtsecurity.utils.JwtUtil;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    /*** 用户登陆生成认证token * @param userName * @param passWord * @return */
    @RequestMapping(value = "/login/{userName}/{passWord}", method = RequestMethod.POST/*,produces = { "application/json;charset=UTF-8"}*/)//如果用浏览器请求测试要改为get测试再改回来
    public String login(@PathVariable String userName, @PathVariable String passWord,Map map) {

        List<User> userList = userService.getUser(userName);
        for (User user : userList) {
            if (user != null && bCryptPasswordEncoder.matches(passWord, user.getPassword())) {
                String token = jwtUtil.createJWT(user.getUid(), user.getUsername(), "user");
                System.out.println("登录   Authorization=Bearer "+token);
                map.put("token", token);
                map.put("userName", user.getUsername());
                return "login";
            }
            }
            return "error";
        }
    }
