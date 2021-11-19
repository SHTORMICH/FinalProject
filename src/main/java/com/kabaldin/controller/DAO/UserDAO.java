package com.kabaldin.controller.DAO;

import com.kabaldin.controller.DAO.entity.User;

public interface UserDAO {

    public boolean registration(String login, String email, String password, String first_name, String last_name, String phone_number);

    public User getUserByLoginAndPassword(String login, String password);

    public User getUserByLogin(String login);
}
