package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.Request;
import com.kabaldin.controller.DAO.entity.User;

import java.util.List;

public interface DBManagerInterface {
// User
    public boolean registration(String login, String email, String password, String first_name, String last_name, String phone_number);

    public User logIn(String login, String password);

    public User.AccessLevel getUserByLoginAndPassword(String login, String password);

    public boolean userIsExist(String login, String password);

    public User getUsersByLogin(String login);

    public List<User> getUsersByRole(int role_id);

    public List<User> getAllUsers();

    public boolean changeUsersInfo(String login, String email, String password, String firstName, String lastName, String phoneNumber);

    public boolean deleteUserByLogin(String login);

// Request
    public int creatRequest(Request request, String description, int total_cost, String user_login);

    /*public boolean lowToBigDate();

    public boolean bigToLowDate();

    public boolean lowToBigTotalCost();

    public boolean bigToLowTotalCost();

    public boolean inWorkCompilationStatus();

    public boolean finishCompilationStatus();*/

    public List<Request> getUserRequests(int userId);

    public boolean updateCompilationStatusToFinish();

    public boolean updatePaymentStatusToPaid();

    public boolean updatePaymentStatusToCanceled();

    public boolean updateDescription(String description);

    public boolean updateTotalCost(int cost, int idRequest);

    public boolean appointMaster(String master, int idRequest);

    public boolean deleteRequest(int request);

// Feedback

    public int addFeedbackForMasterWork (String masterName, String feedback, int rating, int request_id);
}
