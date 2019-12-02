package com.example.me.jwtsecurity.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    User selectUserById(Long uid);

    public void add(User user);

    @Select("select * from user where username = #{username} ")
    public List<User> getUseByName(String userName);

    User findOneByNameAndPwd(String username, String password);

    @Delete("delete from user where uid = #{uid}")
    void removeById(Long uid);
}
