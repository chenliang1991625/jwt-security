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

    User selectUserById(Long id);

    public void add(User user);

    @Select("select * from user where userName = #{userName} ")
    public List<User> getUseByName(String userName);

    User findOneByNameAndPwd(String userName, String passWord);

    @Delete("delete from user where id = #{id}")
    void removeById(Long id);
}
