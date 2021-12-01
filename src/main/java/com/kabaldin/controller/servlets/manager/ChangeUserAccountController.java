package com.kabaldin.controller.servlets.manager;

import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/change/user/account")
public class ChangeUserAccountController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        String login = req.getParameter(LOGIN);
        req.setAttribute(LOGIN, login);
        req.setAttribute("language", language);
        req.getRequestDispatcher("/manager/user_account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter(LOGIN);
        int account = Integer.parseInt(req.getParameter("account"));
        ImpUserDAO.getInstance().updateUserAccountByLogin(account, login);
        resp.sendRedirect(req.getContextPath() + "/manager/users");
    }
}
