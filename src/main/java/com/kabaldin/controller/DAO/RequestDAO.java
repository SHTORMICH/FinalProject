package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.Request;

import java.sql.ResultSet;
import java.util.List;

public interface RequestDAO {

    public boolean creatRequest(String description, String total_cost, String user_login);

    public void saveRequest(Request request);

    public List<Request> getAllUserRequest(String userLogin);
}
