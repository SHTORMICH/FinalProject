package com.kabaldin.controller.DAO.connection;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DBManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find SQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties prop = new Properties();
        try (InputStream resource = DBManager.class.getClassLoader().getResourceAsStream("accessMySql.properties");) {
            prop.load(resource);
        } catch (IOException e) {
            throw new RuntimeException("IOException in getConnection method", e);
        }
        String url = prop.getProperty("connection.url");
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB ", e);
        }
    }
}
