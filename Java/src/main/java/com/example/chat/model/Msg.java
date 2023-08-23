package com.example.chat.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private String name;
    private String msg;

    public Msg(String username, String message) {
        this.msg = message;
        this.name = username;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    Gson gson = new Gson();
    public  String result(String name,String msg){
        Map<String,String> map = new HashMap<>();
        map.put("msg",msg);
        map.put("name",name);
        return gson.toJson(map);
    }
    public String toString() {
        return result(name,msg);
    }
}
