package com.example.chat.tt;

import com.example.chat.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Reader;

public class CS {
    @Test
    public void testSelectAll() throws Exception{
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        Object o = session.selectList("selectAll");
        System.out.println(o);
    }
}
