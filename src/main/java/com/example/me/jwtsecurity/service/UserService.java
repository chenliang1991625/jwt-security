package com.example.me.jwtsecurity.service;

import com.example.me.jwtsecurity.mapper.UserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    public User getSer(Long uid) {
        return userMapper.selectUserById(uid);
    }

    public void save(User user) {

        userMapper.add(user);
    }
//用List<User>作为返回值,是因为根据名字可能会找到多个用户(同名同姓用户)
    public List<User> getUser(String username) {

        return userMapper.getUseByName(username);
    }
//根据用户名和加密后的密码查询
    public User findByNameAndPwd(String username, String password) {
        User user=userMapper.findOneByNameAndPwd(username,password);

        return user;
    }

    public void deleteById(Long uid) {
        userMapper.removeById(uid);
    }

    @Override  //Spring security获取user表中的用户名，密码等数据注入到userDetails 中,实现用户认证和授权
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userMapper.getUseByName(username).get(0);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
       /* List<Role> roles=user.getRoles(); //创建了role角色表并在User类中加List<Roles> 属性,可以打开此处代码
        for (Role role : roles) {
           String  roleName= role.getRoleName();
           authorities.add(new SimpleGrantedAuthority(roleName));
        }*/
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("user");//user是角色名，真正的业务是创建一张角色表role，从表中查询,这里写死
        authorities.add(simpleGrantedAuthority);
        UserDetails userDetails =null;
        if (user!=null){
             userDetails =  new org.springframework.security.core.userdetails.User(
                    user.getPassword(),
                    user.getUsername(),
                    true,
                    true,
                    true,
                    true,
                    authorities
            );
        }
        return userDetails;
    }
}
