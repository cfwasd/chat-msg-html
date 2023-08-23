package com.example.chat.controller;

import com.example.chat.dao.UserMapper;
import com.example.chat.model.RegisterModel;
import com.example.chat.model.users;
import com.example.chat.uilts.MybitsUtlis;
import com.example.chat.uilts.RegisterUilt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
@Slf4j
@RestController
public class Login {
    @Autowired
    MybitsUtlis mybitsUtlis;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RegisterUilt registerUilt;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/login")
    public String login(@RequestBody RegisterModel registerModel){

        String user = registerModel.getUser();
        String pwd = registerModel.getPwd();
        try{
            String u = mybitsUtlis.findByUser(user);
            users users = registerUilt.deserialization(u);
            if (users.getPwd().equals(pwd)){
                log.info("收到登录请求");
                return registerUilt.result("true");

            }
        }catch (Exception e){
            return registerUilt.result("error");
        }

        return registerUilt.result("error");
    }

}
