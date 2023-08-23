package com.example.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class users {
    private String user;
    private String pwd;

    @Override
    public String toString() {
        return "{"+
                "user:"+user+
                ",pwd:"+pwd+
                "}";
    }
    public String select(){

        return pwd;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
