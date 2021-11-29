package com.kabaldin.controller.servlets.master;

import com.kabaldin.controller.DAO.ImpDAO.ImpCompilationStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpPaymentStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.entity.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/table/requests")
public class TableOfRequestsController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);

        Map<Integer, String> compilationStatuses = ImpCompilationStatusDAO.getInstance().getAllCompilationStatus();
        Map<Integer, String> paymentStatus = ImpPaymentStatusDAO.getInstance().getAllPaymentStatus();
        List<Request> requests = ImpRequestDAO.getInstance().getAllUsersRequestForMaster(login);
        req.setAttribute("compilationStatuses", compilationStatuses);
        req.setAttribute("paymentStatus", paymentStatus);
        req.setAttribute("requests", requests);
        getServletContext().getRequestDispatcher("/master/tabla_requests.jsp").forward(req, resp);
    }
}
