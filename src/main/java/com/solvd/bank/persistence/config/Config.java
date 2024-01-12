package com.solvd.bank.persistence.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String DRIVER;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static Integer POOL_SIZE;

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            DRIVER = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            POOL_SIZE = Integer.valueOf(properties.getProperty("poolSize"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            SESSION_FACTORY = new SqlSessionFactoryBuilder()
                    .build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static SqlSessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}