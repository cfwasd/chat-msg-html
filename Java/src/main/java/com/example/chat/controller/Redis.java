package com.example.chat.controller;

import com.example.chat.dao.MsgMapper;
import com.example.chat.model.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Redis {
    @Autowired
    MsgMapper msgMapper;
    @Autowired
    Chat chat;
    @Autowired
    Gson gson;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/select")
    public ArrayList<Map> ref(){
        ArrayList<Map> arr = new ArrayList<>();
        for (Msg m: msgMapper.selectAll()) {
            Gson gson = m.getGson();
            m.setGson(null);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.convertValue(m, Map.class);
            map.remove("gson"); // 移除 gson 字段

            arr.add(map);
        }
        return arr;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/session")
    public void T(){
        for (String name: Chat.m.keySet()) {
            String msg = Chat.m.get(name);
            msgMapper.insert(new Msg(name,msg));
        }

        Chat.m.clear();
    }
}
