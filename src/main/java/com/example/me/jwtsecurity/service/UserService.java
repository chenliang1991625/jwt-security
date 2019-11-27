package com.example.me.jwtsecurity.service;

import com.example.me.jwtsecurity.mapper.UserMapper;
import com.example.me.jwtsecurity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User getSer(int id){
        return userMapper.selectUserById(id);
    }
}
