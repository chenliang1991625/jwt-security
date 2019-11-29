package com.example.me.jwtsecurity.service;

import com.example.me.jwtsecurity.mapper.UserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getSer(Long id) {
        return userMapper.selectUserById(id);
    }

    public void save(User user) {

        userMapper.add(user);
    }
//用List<User>作为返回值,是因为根据名字可能会找到多个用户(同名同姓用户)
    public List<User> getUser(String userName) {

        return userMapper.getUseByName(userName);
    }
//根据用户名和加密后的密码查询
    public User findByNameAndPwd(String userName, String passWord) {
        User user=userMapper.findOneByNameAndPwd(userName,passWord);

        return user;
    }

    public void deleteById(Long id) {
        userMapper.removeById(id);
    }
}
