package com.example.chat.controller;

import com.example.chat.model.RegisterModel;
import com.example.chat.uilts.MybitsUtlis;
import com.example.chat.uilts.RegisterUilt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.rmi.server.LogStream.log;

@Slf4j
@RestController
public class Register {
    @Autowired
    private MybitsUtlis mybitsUtlis;
    @Autowired
    private RegisterUilt indexUilt;
    @PostMapping("/register")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String index(@RequestBody RegisterModel indexModel) throws IOException {
        String user = indexModel.getUser();
        String pwd = indexModel.getPwd();
        try {
            mybitsUtlis.insertUser(user, pwd);
            log.info("注册成功");
            return indexUilt.result("true");

        } catch (Exception e){
            log.info("注册失败");
            return indexUilt.result("false");
        }

    }
}
