package com.solvd.bank.persistence.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String DRIVER;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static Integer POOL_SIZE;

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
    }
}