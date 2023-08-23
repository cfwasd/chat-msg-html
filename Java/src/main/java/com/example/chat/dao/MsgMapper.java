package com.example.chat.dao;

import com.example.chat.model.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface MsgMapper {
    List<Msg> selectAll();
    void insert(Msg user);
}
