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
        String column = req.getParameter("column");
        String sortType = req.getParameter("sortType");
        int prev = tryParse(req.getParameter("prev"));
        int next = tryParse(req.getParameter("next"));
        int offset = tryParse(req.getParameter("offset"));
        String nameOfMasterFilter = req.getParameter("mastersFilter");
        String compilationStatusFilter = req.getParameter("compilationStatusFilter");
        String paymentStatusFilter = req.getParameter("paymentStatusFilter");
        int amountRequestsInDB = ImpRequestDAO.getInstance().countAllRequest();

        if (prev != 0 && offset - prev > 0) {
            offset -= prev;
        } else if (next != 0 && offset + next < amountRequestsInDB) {
            offset += next;
        }
        List<Request> requests = ImpRequestDAO.getInstance().getAllUsersRequestFilter(column, sortType, nameOfMasterFilter, compilationStatusFilter, paymentStatusFilter, 3, offset);

        Map<String, String> mastersNames = ImpUserDAO.getInstance().getAllMasters();
        Map<Integer, String> compilationStatuses = ImpCompilationStatusDAO.getInstance().chooseAllCompilationStatus();
        Map<Integer, String> paymentStatuses = ImpPaymentStatusDAO.getInstance().chooseAllPaymentStatus();
        req.setAttribute("requests", requests);
        req.setAttribute("sortType", sortType);
        req.setAttribute("column", column);
        req.setAttribute("offset", offset);
        req.setAttribute("mastersNames", mastersNames);
        req.setAttribute("compilationStatuses", compilationStatuses);
        req.setAttribute("paymentStatuses", paymentStatuses);
        req.setAttribute("nameOfMasterFilter", nameOfMasterFilter);
        req.setAttribute("compilationStatusFilter", compilationStatusFilter);
        req.setAttribute("paymentStatusFilter", paymentStatusFilter);
        getServletContext().getRequestDispatcher("/manager/users_requests.jsp").forward(req, resp);
    }

    public static int tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
