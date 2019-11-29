package com.example.me.jwtsecurity.controller;

import com.example.me.jwtsecurity.service.UserService;
import com.example.me.jwtsecurity.utils.IdWorker;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IdWorker idWorker;
    @RequestMapping("get/{id}")
    public User GetUser(@PathVariable int id) {
//        SecurityProperties.User user = new SecurityProperties.User();
        User user = userService.getSer(id);
        return user;
    }
//      添加方法不成功，不知道为什么
     /* @Autowired
      @RequestMapping(value = "add",method = RequestMethod.POST)
      public void add(@RequestBody User user) {
        //加密
        if (user!=null){
            user.setId((int) idWorker.nextId());//id用id生成器生成，数据库中的id就不要设置成自增
            String sqlpassWord = bCryptPasswordEncoder.encode(user.getPassWord());//加密后的密码
                  user.setPassWord(sqlpassWord);
            userService.save(user);
        }
//        验证:登录安全校验时再用呗
//        boolean matchesStata = encoding.matches(user.getPassWord(), sqlUser.getPassWord());
      }*/

//    根据登陆名和密码查询
    @RequestMapping("getUser/{userName}/{passWord}")
    public User GetUserByName(@PathVariable String userName, @PathVariable String passWord) {
        List<User> userList = userService.getUser(userName);
//        String encode = bCryptPasswordEncoder.encode("123");
//        System.out.println(encode);
        for (User user : userList) {
            if (user!=null && bCryptPasswordEncoder.matches(passWord,user.getPassWord())){
                return user;
            }
        }
        return null;
    }
}
