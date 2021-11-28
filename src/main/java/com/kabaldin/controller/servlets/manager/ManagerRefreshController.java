package com.kabaldin.controller.servlets.manager;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager/refresh")
public class ManagerRefreshController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfMaster = req.getParameter("masters");
        int compilationStatus = Integer.parseInt(req.getParameter("compilationStatus"));
        int paymentStatus = Integer.parseInt(req.getParameter("paymentStatus"));
        int id = Integer.parseInt(req.getParameter("id"));
        ImpRequestDAO.getInstance().changeMasterInRequest(id, nameOfMaster);
        ImpRequestDAO.getInstance().changeCompilationStatusInRequest(id, compilationStatus);
        ImpRequestDAO.getInstance().changePaymentStatus(id, paymentStatus);
        resp.sendRedirect(req.getContextPath() + "/table/requests");
    }
}
