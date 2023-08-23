package com.example.chat.dao;

import com.example.chat.model.Msg;
import com.example.chat.model.users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {

    // 查询所有用户
    List<users> selectAll();

    // 根据用户名查询用户
//    @Select(value = "select * from users where user = #{user}")
    users findByUser(String user);

    // 新增用户
    int insert(users users);

}