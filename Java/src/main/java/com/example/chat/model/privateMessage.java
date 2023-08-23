package com.example.chat.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class privateMessage {
     String send;
    String receive;
    String message;

    public privateMessage(String send, String receive, String message) {
        this.send = send;
        this.receive = receive;
        this.message = message;
    }

    public privateMessage(String send, String receive) {
        this.send = send;
        this.receive = receive;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    public String result(String send, String receive, String message) throws JsonProcessingException {
        // 构造一个Map对象
        Map<String, String> map = new HashMap<>();
        map.put("send", send);
        map.put("receive", receive);
        map.put("message",message);

        // 将Map序列化为JSON字符串
        String json = objectMapper.writeValueAsString(map);

        return json;
    }
    public String toString(){

        try {
            return result(send,receive,message);
        } catch (JsonProcessingException e) {
            log.info("消息存储失败");
            return "发送失败";
        }
    }


}