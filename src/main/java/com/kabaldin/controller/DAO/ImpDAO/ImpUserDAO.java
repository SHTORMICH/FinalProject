package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.connection.DBManager;
import com.kabaldin.controller.DAO.UserDAO;
import com.kabaldin.controller.DAO.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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

    @Override
    public boolean registration(String login, String email, String password, String firstName, String lastName, String phoneNumber) {
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

    public static void main(String[] args) {
        ImpUserDAO userDao = new ImpUserDAO();
        userDao.registration("Masha234" , "masha23@gmail.com", "12345", "Masha", "DAdah", "380001201020");
    }
}
