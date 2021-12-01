package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/account")
public class IncreaseAccountController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        int account = Integer.parseInt(req.getParameter("account"));
        ImpUserDAO.getInstance().updateUserAccountByLogin(account, login);
        req.setAttribute("account", account);
        resp.sendRedirect(req.getContextPath() + "/user/profile");
    }
}
