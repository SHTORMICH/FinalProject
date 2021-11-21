package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/pay")
public class PayRequestController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int totalCost = Integer.parseInt(req.getParameter("totalCost"));
        int account = Integer.parseInt(req.getParameter("account"));
        String login = req.getParameter("userLogin");
        if (account >= totalCost) {
            ImpUserDAO.getInstance().withdrawingMoney(totalCost, login);
            ImpRequestDAO.getInstance().changePaymentStatusToPaid(id);
        }
        resp.sendRedirect(req.getContextPath() + "/user/profile");
    }
}
