package com.example.chat.dao;

import com.example.chat.model.Msg;
import com.example.chat.model.privateMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface privateMsg {
    List<privateMessage> select(privateMessage msg);
    void insert(privateMessage msg);

    List<privateMessage> selectAll();
}
