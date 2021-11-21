package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    public List<User> getAllUsers();

    public Map<String, String> getAllMasters();

    public String getMasterByLogin(String login);

    public void saveUser(User user);

    public User getUserByLoginAndPassword(String login, String password);

    public User getUserByLogin(String login);

    public boolean updateUserAccountByLogin(int account, String login);

    public boolean deleteUser(String login);

    public boolean withdrawingMoney(int totalCost, String login);
}
