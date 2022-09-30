package com.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static String user;
    private static String password;
    private static String url;
    private static Connection connection;

    private DBManager() {

    }

    public static Connection getConnection() {

//        url = "jdbc:mysql://localhost:3306/studentManagement?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
//        user = "root";
//        password = "root";
        try ( InputStream input = DBManager.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            //System.out.println(prop.getProperty("url"));
            url = prop.getProperty("spring.datasource.url");
            //System.out.println(prop.getProperty("user"));
            user = prop.getProperty("spring.datasource.username");
            //System.out.println(prop.getProperty("password"));
            password = prop.getProperty("spring.datasource.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
