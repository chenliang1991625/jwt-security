package com.example.me.jwtsecurity.controller;

import com.example.me.jwtsecurity.service.UserService;
import com.example.me.jwtsecurity.utils.IdWorker;
import com.example.pojo.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("get/{id}")
    public User GetUser(@PathVariable Long id) {
//        SecurityProperties.User user = new SecurityProperties.User();
        User user = userService.getSer(id);
        return user;
    }
    /* 查询等其它方法都成功,添加方法不成功，不知道为什么，后面再b站上:https://www.bilibili.com/video/av74851468?p=14看了srping securty的认证流程分析
    才明白,srping securty默认用自己内置的UserDetails（User)类,而不用我们自己创建的User类对应的数据库中的表中的数据;如果要实现用我们
    自己的表需要加代码完成（Service 继承UserDetailsService重写loadUserByUsername方法可实现）!但是我这样做了还是不行，我看的是spring security二不是springboot security
    ，那再找springboot security的课程来学学呗(可以吧springboot security自带的登录页面改成自己的，也有可能是springboot版本的问题,解决了:把它复制到下面运行就可以(why？))
    */
     /* @Autowired
      @RequestMapping(value = "add",method = RequestMethod.POST)
      public void add(@RequestBody User user) {
        //加密
        if (user!=null){
            user.setUid(idWorker.nextId());//id用id生成器生成，数据库中的id就不要设置成自增
            String sqlpassWord = bCryptPasswordEncoder.encode(user.getPassword());//加密后的密码
                  user.setPassword(sqlpassWord);
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
            if (user != null && bCryptPasswordEncoder.matches(passWord, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /*** 删除 * @param id
     * @param id*/
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        Claims claims = (Claims) request.getAttribute("admin_claims");//判断是管理员才可删除
        if (claims == null) {
            return "error";
        }
        userService.deleteById(id);
        return "index";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody User user) {
        //加密
        if (user != null) {
            user.setUid(idWorker.nextId());//id用id生成器生成，数据库中的id就不要设置成自增
            String sqlpassWord = bCryptPasswordEncoder.encode(user.getPassword());//加密后的密码
            user.setPassword(sqlpassWord);
            userService.save(user);
        }
    }
}
