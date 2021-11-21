package com.kabaldin.controller.servlets.manager;

import com.kabaldin.controller.DAO.ImpDAO.ImpCompilationStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpPaymentStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/manager/users/requests")
public class UsersRequestsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Request> requests = ImpRequestDAO.getInstance().getAllUsersRequest();
        Map<String, String> mastersName = ImpUserDAO.getInstance().getAllMasters();
        Map<Integer, String> compilationStatus = ImpCompilationStatusDAO.getInstance().chooseAllCompilationStatus();
        Map<Integer, String> paymentStatus = ImpPaymentStatusDAO.getInstance().chooseAllPaymentStatus();
        req.setAttribute("requests", requests);
        req.setAttribute("mastersName", mastersName);
        req.setAttribute("compilationStatus", compilationStatus);
        req.setAttribute("paymentStatus", paymentStatus);
        getServletContext().getRequestDispatcher("/manager/users_requests.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
