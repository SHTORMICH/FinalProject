package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.Request;

import java.util.List;

public interface RequestDAO {

    public boolean creatRequest(String description, int total_cost, String user_login);

    public void saveRequest(Request request);

    public List<Request> getAllUserRequest(String userLogin);

    public List<Request> getAllUsersRequest();

    public boolean changeDescription(int id, String description);

    public boolean changeTotalCost(int id, int totalCost);

    public boolean changePaymentStatusToPaid(int id);

    public boolean changePaymentStatusToCanceled(int id);

    public boolean changeMasterInRequest(int id, String nameOfMaster);

    public boolean changeCompilationStatusInRequest(int id, int compilationStatusId);

}
