package com.kabaldin.controller.servlets;

import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter("password");
        try {
            User user = ImpUserDAO.getInstance().getUserByLoginAndPassword(login, password);
            HttpSession session = req.getSession();
            session.setAttribute(LOGIN, user.getLogin());
            if (user.getRoleId() == 1) {
                resp.sendRedirect("admin.jsp");
            } else if (user.getRoleId() == 2) {
                resp.sendRedirect("admin.jsp");
            } else if (user.getRoleId() == 3) {
                resp.sendRedirect("profileUser");
            } else {
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
