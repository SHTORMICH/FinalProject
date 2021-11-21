package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.connection.DBManager;
import com.kabaldin.controller.DAO.UserDAO;
import com.kabaldin.controller.DAO.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kabaldin.controller.DAO.query.SQLQuery.UserQuery.*;

public class ImpUserDAO implements UserDAO {
    private final Connection connection = DBManager.getConnection();
    private static ImpUserDAO userDao;
    private final Logger logger = Logger.getLogger(ImpUserDAO.class.getName());

    public static ImpUserDAO getInstance() {
        if (userDao == null) {
            userDao = new ImpUserDAO();
        }
        return userDao;
    }

    private boolean registration(String login, String email, String password, String firstName, String lastName, String phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            ps.setString(1, login);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, phoneNumber);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warning("Problem with registration(login, email, password, first_name, last_name, phone_number)");
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)){
            try (ResultSet rs = ps.executeQuery()) {
                users = resultSetToUserList(rs);
            }
        } catch (SQLException throwables) {
            logger.warning("List of users doesn't create!");
        }
        return users;
    }

    @Override
    public Map<String, String> getAllMasters() {
        Map<String, String> masters = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MASTERS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getString("first_name") +
                            " " + rs.getString("last_name");
                    masters.put(rs.getString("login"), fullName);
                }
            }
        } catch (SQLException throwables) {
            logger.warning("No master in DB!");
        }
        return masters;
    }

    @Override
    public String getMasterByLogin(String login) {
        StringBuilder fullName = new StringBuilder();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_MASTERS_BY_LOGIN)){
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fullName.append(rs.getString("first_name"));
                    fullName.append(" ");
                    fullName.append(rs.getString("last_name"));
                }
            }
        } catch (SQLException throwables) {
            logger.warning("Master doesn't find in DB! Parameter String login");
        }
        return fullName.toString();
    }

    private List<User> resultSetToUserList(ResultSet rs) throws SQLException {
        List<User> res = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAccount(rs.getInt("account"));
            user.setRoleId(rs.getInt("role_id"));
            res.add(user);
        }
        return res;
    }

    @Override
    public void saveUser(User user) {
        if (matchLogin(user.getLogin()) && matchPassword(user.getPassword())) {
            ImpUserDAO.getInstance().registration(user.getLogin(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
        } else {
            throw new RuntimeException("Incorrect input login or email or password;");
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Optional<User> user = Optional.ofNullable(ImpUserDAO.userDao.getUserByLogin(login));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        logger.warning("Failed to log in with login - " + login);
        throw new RuntimeException("Login or password are invalid");
    }

    private boolean matchLogin(String login) {
        String regex = "^[a-zA-Z0-9_-]{3,45}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private boolean matchEmail(String email) {
        String regex = "^([a-z0-9_.-]+)@([a-z.-]+).([a-z.]{2,6})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean matchPassword(String password) {
        String regex = "^[a-z0-9_-]{6,45}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setAccount(rs.getInt("account"));
                    user.setRoleId(rs.getInt("role_id"));
                }
            }
        } catch (SQLException throwables) {
            logger.warning("User doesn't create! Parameter String login");
        }
        return user;
    }

    @Override
    public boolean updateUserAccountByLogin(int account, String login) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_INCREMENT_ACCOUNT)){
            ps.setInt(1, account);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("User's" + login + "account doesn't increase. Parameter - int account, String login");
            return false;
        }
    }

    @Override
    public boolean deleteUser(String login) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setString(1, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("User doesn't delete! Parameter String login");
            return false;
        }
    }

    @Override
    public boolean withdrawingMoney(int totalCost, String login) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DECREMENT_ACCOUNT)){
            ps.setInt(1, totalCost);
            ps.setString(2, login);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Account doesn't change! Parameter int totalCost, String login");
            return false;
        }
    }

    public static void main(String[] args) {
        ImpUserDAO userDao = new ImpUserDAO();
        //userDao.registration("Masha234" , "masha23@gmail.com", "12345", "Masha", "DAdah", "380001201020");
        //List<User> allUsers = userDao.getAllUsers();
        //allUsers.stream().forEach(System.out::println);
        System.out.println(userDao.getMasterByLogin("MasterOne"));

    }
}
