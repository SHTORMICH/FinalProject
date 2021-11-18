package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profileUser")
public class Profile extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        User user =  ImpUserDAO.getInstance().getUserByLogin(login);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/user/profile.jsp").forward(req, resp);
    }
}
