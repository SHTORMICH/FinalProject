package com.kabaldin.controller.servlets.master;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/master/choose/request")
public class ChooseRequestController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        int id = Integer.parseInt(req.getParameter("id"));
        ImpRequestDAO.getInstance().changeMasterInRequest(id, login);
        resp.sendRedirect(req.getContextPath() + "/table/requests");
    }
}
