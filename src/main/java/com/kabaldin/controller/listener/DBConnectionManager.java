package com.kabaldin.controller.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private Connection connection;

    public DBConnectionManager(String user, String password, String dbUrl, String driver) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(dbUrl, user, password);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
