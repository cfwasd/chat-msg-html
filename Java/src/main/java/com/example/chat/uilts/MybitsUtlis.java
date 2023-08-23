package com.example.chat.uilts;

import com.example.chat.dao.UserMapper;
import com.example.chat.model.users;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MybitsUtlis {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private UserMapper userMapper;
    public String findByUser(String user) {
        users u = userMapper.findByUser(user);
        return u.toString();
    }

    public void insertUser(String user, String pwd){
        userMapper.insert(new users(user, pwd));
    }
    public String selectAll(){
        Object o = userMapper.selectAll();
        return o.toString();
    }
}
