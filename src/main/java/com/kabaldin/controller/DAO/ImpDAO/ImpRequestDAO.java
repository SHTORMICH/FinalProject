package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.connection.ConnectionPool;
import com.kabaldin.controller.DAO.RequestDAO;
import com.kabaldin.controller.DAO.entity.Request;

import static com.kabaldin.controller.DAO.query.SQLQuery.RequestQuery.*;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpRequestDAO implements RequestDAO {
    ConnectionPool connectionPool = new ConnectionPool(2);
    private final Connection connection = connectionPool.getConnection();
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
    public List<Request> getAllUserRequestByLogin(String userLogin) {
        return getRequests(userLogin, SELECT_ALL_REQUESTS_BY_USER_LOGIN);
    }

    @Override
    public List<Request> getAllUsersRequestForMaster(String login) {
        return getRequests(login, SELECT_ALL_REQUESTS_FOR_MASTER);
    }

    @Override
    public int countAllRequest() {
        int size = 0;
        try (PreparedStatement ps = connection.prepareStatement(COUNT_ALL_REQUESTS)){
            try (ResultSet rs = ps.executeQuery()){
                rs.next();
                size = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.log(Level.WARNING, "", throwables);
        }
        return size;
    }

    public int countAllRequestForUser(String login) {
        int size = 0;
        try (PreparedStatement ps = connection.prepareStatement(COUNT_ALL_REQUESTS)){
            try (ResultSet rs = ps.executeQuery()){
                rs.next();
                size = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.log(Level.WARNING, "", throwables);
        }
        return size;
    }

    private List<Request> getRequests(String userLogin, String selectAllRequestsByUserLogin) {
        List<Request> requests = null;
        try (PreparedStatement ps = connection.prepareStatement(selectAllRequestsByUserLogin)) {
            ps.setString(1, userLogin);
            try (ResultSet resultSet = ps.executeQuery()) {
                requests = resultSetToRequestList(resultSet);
            }
        } catch (SQLException throwables) {
            logger.log(Level.WARNING, "", throwables);
        }
        return requests;
    }

    private List<Request> resultSetToRequestList(ResultSet rs) throws SQLException {
        List<Request> requests = new ArrayList<>();
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
            requests.add(request);
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
            logger.log(Level.WARNING, "", throwables);
        }
        return requests;
    }

    @Override
    public List<Request> getAllUsersRequestFilter(String login, String column, String changer, String master, String compilationStatus, String paymentStatus, int limit, int offset) {
        List<Request> requests = null;
        StringBuilder query = new StringBuilder();
        query.append(SELECT_REQUEST_BY_FILTER);
        if (login != null || master != null && !master.equals("") ||
                compilationStatus != null && !compilationStatus.equals("") ||
                paymentStatus != null && !paymentStatus.equals("")) {
            query.append(" WHERE");
            if (login != null) {
                query.append(" user_login=")
                        .append("\'")
                        .append(login)
                        .append("\'");
            }
            if (login != null && master != null && !master.equals("")) {
                query.append(" AND");
            }
            if (master != null && !master.equals("")) {
                query.append(" master=")
                        .append("\'")
                        .append(master)
                        .append("\'");
            }
            if (compilationStatus != null && !compilationStatus.equals("")) {
                query.append(" AND")
                        .append(" compilation_status_id=")
                        .append(Integer.parseInt(compilationStatus.replaceAll("\\s+","")));
            }
            if (paymentStatus != null && !paymentStatus.equals("")) {
                query.append(" AND")
                        .append(" payment_status_id=")
                        .append(Integer.parseInt(paymentStatus.replaceAll("\\s+","")));
            }
        }
        if (column != null && !column.equals("")) {
            query.append(" ORDER BY ")
                    .append(column)
                    .append(" ")
                    .append(changer);
        }
        query.append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset)
                .append(";");
        try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
            try (ResultSet rs = ps.executeQuery()) {
                requests = resultSetToRequestList(rs);
            }
        } catch (SQLException throwables) {
            logger.log(Level.WARNING, "", throwables);
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
    public boolean changePaymentStatus(int id, int paymentStatusId) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS)){
            ps.setInt(1, paymentStatusId);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            logger.warning("Payment status doesn't change! Parameters int id, int paymentStatusId");
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

    public static void main(String[] args) {
        ImpRequestDAO requestDAO = new ImpRequestDAO();
        StringBuilder result = new StringBuilder();
        List<Request> requestList = requestDAO.getAllUsersRequestFilter(null,null, null, null, null, null, 3, 3);
        for (Request el:requestList) {
            result.append(el.getId())
                    .append(" ")
                    .append(el.getUserLogin())
                    .append(" ")
                    .append(el.getTotalCost())
                    .append(" ")
                    .append(el.getMaster())
                    .append(" ")
                    .append(el.getCompilationStatusId())
                    .append(" ")
                    .append(el.getPaymentStatusId())
                    .append(" ")
                    .append(el.getDate())
                    .append(System.lineSeparator());

        }
        System.out.println(result);
    }
}
