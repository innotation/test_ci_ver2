package com.cjoa.wms.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory == null) {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
                if (inputStream == null) {
                    throw new RuntimeException("MyBatis configuration file not found: config/mybatis-config.xml");
                }
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Error loading MyBatis configuration: " + e.getMessage(), e);
            }
        }
        return sqlSessionFactory.openSession(false);
    }

}
