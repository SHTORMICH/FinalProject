package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.PaymentStatusDAO;
import com.kabaldin.controller.DAO.connection.ConnectionPool;
import com.kabaldin.controller.DAO.entity.PaymentStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.kabaldin.controller.DAO.query.SQLQuery.PaymentStatusQuery.*;

public class ImpPaymentStatusDAO implements PaymentStatusDAO {
    ConnectionPool connectionPool = new ConnectionPool(2);
    private final Connection connection = connectionPool.getConnection();
    private static ImpPaymentStatusDAO paymentStatusDAO;
    private final Logger logger = Logger.getLogger(ImpPaymentStatusDAO.class.getName());

    public static ImpPaymentStatusDAO getInstance() {
        if (paymentStatusDAO == null) {
            paymentStatusDAO = new ImpPaymentStatusDAO();
        }
        return paymentStatusDAO;
    }

    @Override
    public Map<Integer, String> getAllPaymentStatus() {
        Map<Integer, String> status = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PAYMENT_STATUS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PaymentStatus paymentStatus = new PaymentStatus();
                    paymentStatus.setId(rs.getInt("id"));
                    paymentStatus.setPaymentStatus(rs.getString("payment_status"));
                    status.put(paymentStatus.getId(), paymentStatus.getPaymentStatus());
                }
            }
        } catch (SQLException throwables) {
            logger.warning("Doesn't find any payment status!");
        }
        return status;
    }

    public static void main(String[] args) {
        Map<Integer, String> paymentStatus = ImpPaymentStatusDAO.getInstance().getAllPaymentStatus();
        System.out.println(paymentStatus.get(1));
        System.out.println(paymentStatus.get(2));

    }
}
