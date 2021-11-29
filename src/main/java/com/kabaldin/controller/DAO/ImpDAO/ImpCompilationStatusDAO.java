package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.CompilationStatusDAO;
import com.kabaldin.controller.DAO.connection.ConnectionPool;
import com.kabaldin.controller.DAO.entity.CompilationStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import static com.kabaldin.controller.DAO.query.SQLQuery.CompilationStatusQuery.*;

public class ImpCompilationStatusDAO implements CompilationStatusDAO {
    ConnectionPool connectionPool = new ConnectionPool(2);
    private final Connection connection = connectionPool.getConnection();
    private static ImpCompilationStatusDAO compilationStatusDAO;
    private final Logger logger = Logger.getLogger(ImpCompilationStatusDAO.class.getName());

    public static ImpCompilationStatusDAO getInstance() {
        if (compilationStatusDAO == null) {
            compilationStatusDAO = new ImpCompilationStatusDAO();
        }
        return compilationStatusDAO;
    }

    @Override
    public Map<Integer, String> getAllCompilationStatus() {
        Map<Integer, String> status = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_COMPILATION_STATUS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CompilationStatus compilationStatus = new CompilationStatus();
                    compilationStatus.setId(rs.getInt("id"));
                    compilationStatus.setCompilationStatus(rs.getString("compilation_status"));
                    status.put(compilationStatus.getId(), compilationStatus.getCompilationStatus());
                }
            }
        } catch (SQLException throwables) {
            logger.warning("Doesn't find any compilation status!");
        }
        return status;
    }

    public static void main(String[] args) {
        ImpCompilationStatusDAO compilationStatus = new ImpCompilationStatusDAO();
        Map<Integer, String> compilationStatuses = compilationStatus.getAllCompilationStatus();
        System.out.println(compilationStatuses.keySet());
        System.out.println(compilationStatuses.get(2));

    }
}
