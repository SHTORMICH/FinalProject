package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.connection.DBManager;
import com.kabaldin.controller.DAO.RequestDAO;
import com.kabaldin.controller.DAO.entity.Request;

import static com.kabaldin.controller.DAO.query.SQLQuery.RequestQuery.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ImpRequestDAO implements RequestDAO {
    private final Connection connection = DBManager.getConnection();
    private static ImpRequestDAO requestDAO;
    private final Logger logger = Logger.getLogger(ImpRequestDAO.class.getName());

    public static ImpRequestDAO getInstance() {
        if (requestDAO == null) {
            requestDAO = new ImpRequestDAO();
        }
        return requestDAO;
    }

    @Override
    public boolean creatRequest(String description, int totalCost, String userLogin) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, description);
            ps.setInt(2, totalCost);
            ps.setString(3, userLogin);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1) {
                try (ResultSet id = ps.getGeneratedKeys()) {
                    if (id.next()) {
                        id.getInt(1);
                    }
                }
            }
            return true;
        } catch (SQLException throwables) {
            logger.warning("New request doesn't create! Parameters(Request request, String description, int total_cost, String user_login)");
        }
        return false;
    }

    @Override
    public void saveRequest(Request request) {
        ImpRequestDAO.getInstance().creatRequest(request.getDescription(), request.getTotalCost(), request.getUserLogin());
    }

    @Override
    public List<Request> getAllUserRequest(String userLogin) {
        List<Request> requests = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REQUESTS_BY_USER_LOGIN)) {
            ps.setString(1, userLogin);
            try (ResultSet resultSet = ps.executeQuery()) {
                requests = resultSetToRequestList(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getAllUsersRequest() {
        List<Request> requests = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REQUESTS)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                requests = resultSetToRequestList(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return requests;
    }

    @Override
    public boolean changeDescription(int id, String description) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DESCRIPTION)){
            ps.setString(1, description);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Description doesn't change! Parameter String login.");
            return false;
        }
    }

    @Override
    public boolean changeTotalCost(int id, int totalCost) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TOTAL_COST)) {
            ps.setInt(1, totalCost);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Total cost doesn't change! Parameters int id, int totalCost");
            return false;
        }
    }

    @Override
    public boolean changePaymentStatusToPaid(int id) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS_TO_PAID)){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Payment status doesn't change to paid! Parameter int id");
            return false;
        }
    }

    @Override
    public boolean changePaymentStatusToCanceled(int id) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS_TO_CANCELED)){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Payment status doesn't change to canceled! Parameter int id");
            return false;
        }
    }

    @Override
    public boolean changeMasterInRequest(int id, String nameOfMaster) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MASTER)) {
            ps.setString(1, nameOfMaster);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Master doesn't change! Parameters int id, String nameOfMaster");
            return false;
        }
    }

    @Override
    public boolean changeCompilationStatusInRequest(int id, int compilationStatusId) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_COMPILATION_STATUS)) {
            ps.setInt(1, compilationStatusId);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Compilation status doesn't change! Parameters int id, int compilationStatusId");
            return false;
        }
    }

    private List<Request> resultSetToRequestList(ResultSet rs) throws SQLException {
        List<Request> requests = new ArrayList<>();
        while (rs.next()) {
            Request request = new Request();
            request.setId(rs.getInt("id"));
            request.setDescription(rs.getString("description"));
            String masterLogin = rs.getString("master");
            request.setMaster(ImpUserDAO.getInstance().getMasterByLogin(masterLogin));
            request.setDate(rs.getString("date"));
            request.setTotalCost(rs.getInt("total_cost"));
            request.setUserLogin(rs.getString("user_login"));
            request.setCompilationStatusId(rs.getInt("compilation_status_id"));
            request.setPaymentStatusId(rs.getInt("payment_status_id"));
            requests.add(request);
        }
        return requests;
    }

    public static void main(String[] args) {
        ImpRequestDAO requestDAO = new ImpRequestDAO();
        List<Request> requestList = requestDAO.getAllUsersRequest();
        requestList.stream().forEach(System.out::println);
    }
}
