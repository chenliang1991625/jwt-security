package com.example.me.jwtsecurity.controller;

import com.example.me.jwtsecurity.service.UserService;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;
    /*** 用户登陆 * @param userName * @param passWord * @return */
    @RequestMapping(value = "/login/{userName}/{passWord}", method = RequestMethod.POST)//如果用浏览器请求测试要改为get测试再改回来
    public String login(@PathVariable String userName, @PathVariable String passWord, Model model) {

        List<User> userList = userService.getUser(userName);
        for (User user : userList) {
            if (user!=null && bCryptPasswordEncoder.matches(passWord,user.getPassWord())){
                model.addAttribute("user",user);
                return "login";
            }
        }
        model.addAttribute("user", "没查到内容");
        return "error";
    }
}
