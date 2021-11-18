package com.kabaldin.controller.DAO.connection;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class DBManager implements DBManagerInterface {

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

    /*@Override
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
            DBManager.getInstance().registration(user.getLogin(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
        } else {
            throw new RuntimeException("Incorrect input login or email or password;");
        }
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
    public User logIn(String login, String password) {
        return null;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        Optional<User> user = Optional.ofNullable(dbManager.getUserByLogin(login));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        logger.warning("Failed to log in with login - " + login);
        throw new RuntimeException("Login or password are invalid");
    }

    /*@Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ONE_USER_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.getString("login").equals(login) &&
                        rs.getString("password").equals(password)) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
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
    public List<User> getUsersByRole(int roleId) {
        List<User> res = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ROLE)) {
            ps.setInt(1, roleId);
            try (ResultSet resultSet = ps.executeQuery()) {
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

    private List<User> resultSetToUserList(ResultSet rs) throws SQLException {
        List<User> res = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
            res.add(user);
        }
        return res;
    }

    @Override
    public boolean changeUsersInfo(String login, String email, String password, String firstName, String lastName, String phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_LOGIN)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, phoneNumber);
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

    public void saveRequest(Request request) {
        DBManager.getInstance().creatRequest(request.getDescription(), request.getTotalCost(), request.getUserLogin());
    }*/

    /*@Override
    public int creatRequest(String description, String totalCost, String userLogin) {
        int newRowId = 0;
        try (PreparedStatement ps = connection.prepareStatement(ADD_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, description);
            ps.setString(2, totalCost);
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

    @Override
    public List<Request> getUserRequests(int userId) {
        List<Request> res = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REQUESTS)) {
            ps.setInt(1, userId);
            try (ResultSet resultSet = ps.executeQuery()) {
                res = resultSetToRequestList(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    private List<Request> resultSetToRequestList(ResultSet rs) throws SQLException {
        List<Request> res = new ArrayList<>();
        while (rs.next()) {
            Request request = new Request();
            request.setId(rs.getInt("id"));
            request.setDescription(rs.getString("description"));
            request.setMaster(rs.getString("master"));
            request.setDate(rs.getString("date"));
            request.setTotalCost(rs.getString("total_cost"));
            request.setUserLogin(rs.getString("user_login"));
            request.setCompilationStatusId(rs.getInt("compilation_status_id"));
            request.setPaymentStatusId(rs.getInt("payment_status_id"));
            res.add(request);
        }
        return res;
    }*/

    /*@Override
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

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        System.out.println(dbManager.getUserByLogin("MainManeger").getRoleId());
        dbManager.registration("Masha23" , "masha23@gmail.com", "12345", "Masha", "DAdah", "380001201020");
    }*/

}
