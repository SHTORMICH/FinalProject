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
    public boolean creatRequest(String description, String totalCost, String userLogin) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, description);
            ps.setString(2, totalCost);
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
        List<Request> res = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REQUESTS_BY_USER_LOGIN)) {
            ps.setString(1, userLogin);
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
    }

    public static void main(String[] args) {
        ImpRequestDAO requestDAO = new ImpRequestDAO();
        //requestDAO.creatRequest("I need help", "10" , "Masha23");
        List<Request> requestList = requestDAO.getAllUserRequest("Masha23");
        requestList.stream().forEach(System.out::println);
    }
}
