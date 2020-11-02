package edu.eci.cvds.test.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class LabInfoTestUtil {

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory;
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config-h2.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
        return sqlSessionFactory;
    }
}
