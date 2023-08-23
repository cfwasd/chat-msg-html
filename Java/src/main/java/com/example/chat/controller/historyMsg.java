package com.example.chat.controller;

import com.example.chat.dao.privateMsg;
import com.example.chat.model.historyModel;
import com.example.chat.model.privateMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class historyMsg {
    @Autowired
    privateMsg privateMsg;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/history")
    public void historyPri(@RequestBody historyModel p){
//        System.out.println(p.getSend()+p.getReceive()+p.getMessage());
        privateMsg.insert(new privateMessage(p.getSend(),p.getReceive(),p.getMessage()));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/private")
    public List allmessage(@RequestBody historyModel p){
//        System.out.println(p.getSend()+p.getReceive());
        return privateMsg.select(new privateMessage(p.getSend(),p.getReceive()));
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/p")
    public List allmsg(){
        return privateMsg.selectAll();
    }

}
