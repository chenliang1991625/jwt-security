package com.example.me.jwtsecurity.controller;

import com.example.me.jwtsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:陈亮
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/jwt")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("get/{id}")
    public String GetUser(@PathVariable int id) {
        return userService.getSer(id).toString();
    }
}
