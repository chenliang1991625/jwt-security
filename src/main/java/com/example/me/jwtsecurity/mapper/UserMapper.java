package com.example.me.jwtsecurity.mapper;

import com.example.me.jwtsecurity.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Repository
@Mapper
public interface UserMapper {
    User selectUserById(int id);
}
