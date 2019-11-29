package com.example.me.jwtsecurity;


import com.example.me.jwtsecurity.mapper.UserMapper;
import com.example.me.jwtsecurity.utils.IdWorker;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JwtSecurityApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    IdWorker idWorker;
    @Test
    void contextLoads() {
    }
    @Test
    void addUser(){
        int i=0;
        try {
            userMapper.add(new User(idWorker.nextId(),"晨晨"+i++,bCryptPasswordEncoder.encode("123"),"chenchen"+i++));
            System.out.println("添加用户成功");
        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
        } finally {
        }
    }
}
