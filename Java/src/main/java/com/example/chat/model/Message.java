package com.example.chat.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Message {
    private String name;
    private String msg;
}
