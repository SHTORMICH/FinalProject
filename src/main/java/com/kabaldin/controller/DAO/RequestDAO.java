package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.Request;

import java.util.List;

public interface RequestDAO {

    public boolean creatRequest(String description, int total_cost, String user_login);

    public void saveRequest(Request request);

    public List<Request> getAllUserRequestByLogin(String userLogin);

    public List<Request> getAllUsersRequestForMaster(String login);

    public int countAllRequest();

    public int countAllRequestForUser(String login, String master, String compilationStatus, String paymentStatus);

    public List<Request> getAllUsersRequest();

    //public List<Request> getAllUsersRequestByColumn(String column);

    public List<Request> getAllUsersRequestFilter(String login, String column, String changer, String master, String compilationStatus, String paymentStatus, int limit, int offset);

    public boolean changeDescription(int id, String description);

    public boolean changeTotalCost(int id, int totalCost);

    public boolean changePaymentStatusToPaid(int id);

    public boolean changePaymentStatusToCanceled(int id);

    public boolean changePaymentStatus(int id, int paymentStatusId);

    public boolean changeMasterInRequest(int id, String nameOfMaster);

    public boolean changeCompilationStatusInRequest(int id, int compilationStatusId);

}
