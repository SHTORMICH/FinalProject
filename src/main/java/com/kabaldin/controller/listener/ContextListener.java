package com.kabaldin.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String user = context.getInitParameter("user");
        String password = context.getInitParameter("password");
        String dbUrl = context.getInitParameter("dbUrl");
        String driver = context.getInitParameter("driver");
        try {
            DBConnectionManager dbManager = new DBConnectionManager(user, password, dbUrl, driver);
            Connection connection = dbManager.getConnection();
            context.setAttribute("dbConnection", connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ((Connection)sce.getServletContext().getAttribute("dbConnection")).close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
