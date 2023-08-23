package com.example.chat.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class historyModel {
    String send;
    String receive;
    String message;
}
