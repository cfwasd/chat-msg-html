package com.example.chat.controller;


import com.google.gson.Gson;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
@ServerEndpoint("/chat/{username}")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Chat {
    /**
     * 线程安全的无序的集合
     */
    public static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();

    /**
     * 存储在线连接数
     */
    public static final Map<String, Session> session_pool = new HashMap<>();
    public static  Map<Session, String> session_user = new HashMap<>();
    public static ArrayList<String> users = new ArrayList<>();
    public static Map<String,String> m = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username")String username){
        sessions.add(session);
        session_pool.put(username,session);
        session_user.put(session,username);
        all();
        String msg = "【WebSocket消息】有新的连接，总数为：" + names().size();
        sendAllMessage(result("all",msg));
        log.info(msg);
    }
    @OnClose
    public void onClose(Session session) {
        try {
            sessions.remove(session);
            session_user.remove(session);
            all();
            if (names().size() == 0){
                log.info("【WebSocket消息】当前无连接");
            }else {
                String msg = "【WebSocket消息】连接断开，总数为：" + names().size();
                log.info(msg);
                sendAllMessage(result("all",msg));
            }

        } catch (Exception e) {
            log.info("发送出错");
        }
    }


    @OnMessage
    public void onMessage(String message,@PathParam(value = "username")String username) {
        String msg = "【WebSocket消息】收到客户端消息：" + message;
        log.info(msg);
        m.put(username,message);
        sendAllMessage(result(username,message));
    }




    /**
     * 此为广播消息
     *
     * @param message 消息
     */
    public void sendAllMessage(String message) {
        log.info("【WebSocket消息】广播消息：" + message);
        for (Session session : sessions) {
            try {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                log.info("发送出错");
            }
        }
    }
    /**
     * 此为单点消息(多人)
     *
     * @param userIds 用户编号列表
     * @param message 消息
     */
    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = session_pool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【WebSocket消息】单点消息：" + message);
                    session.getAsyncRemote().sendText(result(userId,message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public ArrayList<String> names(){
        ArrayList<String> arr = new ArrayList<>();
        for (Session s: session_user.keySet()) {
            String name = session_user.get(s);
            arr.add(name);
        }

        return arr;
    }
    Gson gson = new Gson();
    public  String result(String name,String msg){
        Map<String,String> map = new HashMap<>();
        map.put("msg",msg);
        map.put("name",name);
        return gson.toJson(map);
    }
    public  void all(){
        users.clear();
        users.addAll(session_pool.keySet());
    }

}
