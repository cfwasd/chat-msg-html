package com.example.chat.uilts;

import com.example.chat.model.users;
import com.google.gson.Gson;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegisterUilt {

    Gson gson = new Gson();
    public  String result(String msg){
        Map<String,String> map = new HashMap<>();
        map.put("result",msg);
        return gson.toJson(map);
    }
    public  String result(String msg,String name){
        Map<String,String> map = new HashMap<>();
        map.put(name,msg);
        return gson.toJson(map);
    }
    public users deserialization(String str){
        users users = gson.fromJson(str, users.class);

        return users;
    }
}
