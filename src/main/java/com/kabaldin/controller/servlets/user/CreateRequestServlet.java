package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.entity.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/creatRequest")
public class CreateRequestServlet extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/creatRequest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        String description = req.getParameter("description");
        String totalCost = req.getParameter("total_cost");
        Request request = new Request(description, totalCost, login);
        ImpRequestDAO.getInstance().saveRequest(request);
        getServletContext().getRequestDispatcher("/profileUser").forward(req, resp);
    }
}
