package com.kabaldin.controller.DAO.ImpDAO;

import com.kabaldin.controller.DAO.RoleDAO;
import com.kabaldin.controller.DAO.connection.ConnectionPool;
import com.kabaldin.controller.DAO.entity.CompilationStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.kabaldin.controller.DAO.query.SQLQuery.RoleQuery.*;

public class ImpRoleDAO implements RoleDAO {
    ConnectionPool connectionPool = new ConnectionPool(2);
    private final Connection connection = connectionPool.getConnection();
    private static ImpRoleDAO roleDAO;
    private final Logger logger = Logger.getLogger(ImpRoleDAO.class.getName());

    public static ImpRoleDAO getInstance() {
        if (roleDAO == null) {
            roleDAO = new ImpRoleDAO();
        }
        return roleDAO;
    }

    @Override
    public Map<Integer, String> getAllRoles() {
        Map<Integer, String> roles = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ROLE)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CompilationStatus compilationStatus = new CompilationStatus();
                    compilationStatus.setId(rs.getInt("id"));
                    compilationStatus.setCompilationStatus(rs.getString("role"));
                    roles.put(compilationStatus.getId(), compilationStatus.getCompilationStatus());
                }
            }
        } catch (SQLException throwables) {
            logger.warning("Doesn't find any compilation status!");
        }
        return roles;
    }

    public static void main(String[] args) {
        ImpRoleDAO roleDAO = new ImpRoleDAO();
        Map<Integer, String> allRoles = roleDAO.getAllRoles();
        System.out.println(allRoles.get(1));
        System.out.println(allRoles.get(2));
        System.out.println(allRoles.get(3));
    }
}
