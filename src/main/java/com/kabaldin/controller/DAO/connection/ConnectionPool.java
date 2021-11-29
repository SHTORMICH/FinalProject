package com.kabaldin.controller.DAO.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private final String fileName = "accessMySql.properties";
    private int maxPoolSize = 10;
    private int connNum = 0;

    private static final String SQL_VERIFICATION_CONN = "SELECT 1";
    private static Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find SQL Driver", e);
        }
    }

    public ConnectionPool(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        if (isFull()) {
            try {
                throw new SQLException("The connection poll is full");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connection = getConnectionFromPool();
        if (connection == null) {
            connection = createNewConnectionFromPool();
        }
        try {
            connection = makeAvailable(connection);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "", e);
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(connection)) {
            throw new SQLException("The connection id returned already or it isn't for this pool");
        }
        freePool.push(connection);
    }

    private synchronized  boolean isFull() {
        return ((freePool.size() == 0)) && (connNum >= maxPoolSize);
    }

    private Connection createNewConnectionFromPool() {
        Connection connection = createNewConnection();
        connNum++;
        occupiedPool.add(connection);
        return connection;
    }

    private Connection createNewConnection() {
        Properties prop = new Properties();
        try (InputStream resource = DBManager.class.getClassLoader().getResourceAsStream(fileName)) {
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

    private Connection getConnectionFromPool() {
        Connection connection = null;
        if (freePool.size() > 0) {
            connection = freePool.pop();
            occupiedPool.add(connection);
        }
        return connection;
    }

    private Connection makeAvailable(Connection connection) throws SQLException {
        if (isConnectionAvailable(connection)) {
            return connection;
        }
        occupiedPool.remove(connection);
        connNum--;
        connection.close();

        connection =createNewConnection();
        occupiedPool.add(connection);
        connNum++;
        return connection;
    }

    private boolean isConnectionAvailable(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.executeQuery(SQL_VERIFICATION_CONN);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
}
