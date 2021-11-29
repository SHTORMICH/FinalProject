package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.FeedbackDAO;
import com.kabaldin.controller.DAO.connection.ConnectionPool;

import java.sql.*;
import java.util.logging.Logger;

import static com.kabaldin.controller.DAO.query.SQLQuery.FeedbackQuery.*;

public class ImpFeedbackDAO implements FeedbackDAO {
    ConnectionPool connectionPool = new ConnectionPool(2);
    private final Connection connection = connectionPool.getConnection();
    private static ImpFeedbackDAO feedbackDAO;
    private final Logger logger = Logger.getLogger(ImpUserDAO.class.getName());

    public static ImpFeedbackDAO getInstance() {
        if (feedbackDAO == null) {
            feedbackDAO = new ImpFeedbackDAO();
        }
        return feedbackDAO;
    }

    @Override
    public boolean addFeedback(int idRequest, String loginMaster, String feedback) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK_TO_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, loginMaster);
            ps.setString(2, feedback);
            ps.setInt(3, idRequest);
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
            logger.warning("Feedback doesn't add! Parameters int id, String loginMaster, String feedback");
            return false;
        }
    }

    public static void main(String[] args) {
        ImpFeedbackDAO feedbackDAO = new ImpFeedbackDAO();
        feedbackDAO.addFeedback(2, "MasterOne", "cool master");
    }
}
