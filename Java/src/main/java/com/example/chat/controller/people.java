package com.example.chat.controller;

import com.example.chat.dao.UserMapper;
import com.example.chat.model.users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class people {
    @Autowired
    Chat chat;
    @Autowired
    UserMapper userMapper;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/refresh")
    public ArrayList<String> ref(){

        log.info("当前在线人数"+chat.names().size());
        return chat.names();
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/alluser")
    public ArrayList<String> alluser(){
        ArrayList<String> arr = new ArrayList<>();
        for (users name: userMapper.selectAll()) {
            arr.add(name.getUser());
        }
        return arr;
    }
}
