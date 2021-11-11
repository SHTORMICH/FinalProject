package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.Request;
import com.kabaldin.controller.DAO.entity.User;
import static com.kabaldin.controller.DAO.SQLQuery.UserQuery.*;
import static com.kabaldin.controller.DAO.SQLQuery.RequestQuery.*;
import static com.kabaldin.controller.DAO.SQLQuery.FeedbackQuery.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DBManager implements DBManagerInterface{
    public Connection connection;
    private final Logger logger = Logger.getLogger(DBManager.class.getName());

    private DBManager() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            logger.warning("Problem with connection!");
        }
    }

    public Connection getConnection() throws SQLException {
        Properties prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("accessMySql.properties")) {
            prop.load(fileInputStream);
        } catch (IOException e) {
            logger.warning("Problem with load properties");
            return null;
        }
        String url = prop.getProperty("connection.url");
        return DriverManager.getConnection(url);
    }

    @Override
    public boolean registration(String login, String email, String password, String firstName, String lastName, int phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            ps.setString(1, login);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setInt(6, phoneNumber);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warning("Problem with registration(login, email, password, first_name, last_name, phone_number)");
            return false;
        }
    }

    @Override
    public User logIn(String login, String password) {
        return null;
    }

    @Override
    public User getUsersByLogin(String login) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                user = new User();
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setPhoneNumber(rs.getInt("phone_number"));
            }
        } catch (SQLException throwables) {
            logger.warning("User doesn't create! Parameter String login");
        }
        return user;
    }


    @Override
    public List<User> getUsersByRole(int roleId) {
        List<User> res = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ROLE)) {
            ps.setInt(1, roleId);
            try (ResultSet resultSet = ps.executeQuery()){
                res = resultSetToUserList(resultSet);
            }
        } catch (SQLException throwables) {
            logger.warning("List of users by role doesn't create!");
        }
        return res;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)) {
            try (ResultSet rs = ps.executeQuery()) {
                usersList = resultSetToUserList(rs);
            }
        } catch (SQLException throwables) {
            logger.warning("List of users doesn't create!");
        }
        return usersList;
    }

    private List<User> resultSetToUserList (ResultSet rs) throws SQLException {
        List<User> res = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getInt("phone_number"));
            res.add(user);
        }
        return res;
    }

    @Override
    public boolean changeUsersInfo(String login, String email, String password, String firstName, String lastName, int phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_LOGIN)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setInt(5, phoneNumber);
            ps.setString(6, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Info doesn't change! String login, String email, String password, String firstName, String lastName, int phoneNumber");
            return false;
        }

    }

    @Override
    public boolean deleteUserByLogin(String login) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setString(1, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("User doesn't delete! String login");
            return false;
        }
    }

    @Override
    public int creatRequest(Request request, String description, int totalCost, String userLogin) {
        int newRowId = 0;
        try (PreparedStatement ps = connection.prepareStatement(ADD_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, description);
            ps.setInt(2, totalCost);
            ps.setString(3, userLogin);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        newRowId = id.getInt(1);
                    }
                }
            }
        } catch (SQLException throwables) {
            logger.warning("New request doesn't create! Parameters(Request request, String description, int total_cost, String user_login)");
        }
        return newRowId;
    }

    /*@Override
    public boolean lowToBigDate() {
        return false;
    }

    @Override
    public boolean bigToLowDate() {
        return false;
    }

    @Override
    public boolean lowToBigTotalCost() {
        return false;
    }

    @Override
    public boolean bigToLowTotalCost() {
        return false;
    }

    @Override
    public boolean inWorkCompilationStatus() {
        return false;
    }

    @Override
    public boolean finishCompilationStatus() {
        return false;
    }*/

    @Override
    public List<Request> getUserRequests(int userId) {
        List<Request> res = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REQUESTS)) {
            ps.setInt(1, userId);
            try (ResultSet resultSet = ps.executeQuery()){
                res = resultSetToRequestList(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private List<Request> resultSetToRequestList (ResultSet rs) throws SQLException {
        List<Request> res = new ArrayList<>();
        while (rs.next()) {
            Request request = new Request();
            request.setId(rs.getInt("id"));
            request.setDescription(rs.getString("description"));
            request.setMaster(rs.getString("master"));
            request.setDate(rs.getString("date"));
            request.setTotalCost(rs.getInt("total_cost"));
            request.setUserLogin(rs.getString("user_login"));
            request.setCompilationStatusId(rs.getInt("compilation_status_id"));
            request.setPaymentStatusId(rs.getInt("payment_status_id"));
            res.add(request);
        }
        return res;
    }

    @Override
    public boolean updateCompilationStatusToFinish() {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPILATION_STATUS)) {
            return true;
        } catch (SQLException e) {
            logger.warning("Compilation status doesn't changed to Finish");
            return false;
        }
    }

    @Override
    public boolean updatePaymentStatusToPaid() {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS_TO_PAID)) {
            return true;
        } catch (SQLException e) {
            logger.warning("Payment status doesn't changed to Paid");
            return false;
        }
    }

    @Override
    public boolean updatePaymentStatusToCanceled() {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS_TO_CANCELED)) {
            return true;
        } catch (SQLException e) {
            logger.warning("Payment status doesn't changed to Canceled");
            return false;
        }
    }

    @Override
    public boolean updateDescription(String description) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DESCRIPTION)) {
            ps.setString(1, description);
            return true;
        } catch (SQLException throwables) {
            logger.warning("Description doesn't change! Parameter String description");
            return false;
        }
    }

    @Override
    public boolean updateTotalCost(int cost, int idRequest) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TOTAL_COST)) {
            ps.setInt(1, cost);
            ps.setInt(2, idRequest);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Total cost doesn't change! parameters int cost, int idRequest");
            return false;
        }
    }

    @Override
    public boolean appointMaster(String master, int idRequest) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MASTER)) {
            ps.setString(1, master);
            ps.setInt(2, idRequest);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Master doesn't appoint! parameters String master, int idRequest");
            return false;
        }
    }

    @Override
    public boolean deleteRequest(int request) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_REQUEST)) {
            ps.setInt(1, request);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Request doesn't delete! Parameters int request");
            return false;
        }
    }

    @Override
    public int addFeedbackForMasterWork(String masterName, String feedback, int rating, int requestId) {
        int newRowId = 0;
        try (PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_TO_ORDER)) {
            ps.setString(1, masterName);
            ps.setString(2, feedback);
            ps.setInt(3, rating);
            ps.setInt(4, requestId);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        newRowId = id.getInt(1);
                    }
                }
            }
        } catch (SQLException throwables) {
            logger.warning("New feedback doesn't create! Parameters(String masterName, String feedback, int rating, int request_id)");
        }
        return newRowId;
    }


}
