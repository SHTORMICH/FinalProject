package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpCompilationStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpPaymentStatusDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.Request;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/profile")
public class ProfileController extends HttpServlet {
    private static final String LOGIN = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        String column = req.getParameter("column");
        String sortType = req.getParameter("sortType");
        int prev = tryParse(req.getParameter("prev"));
        int next = tryParse(req.getParameter("next"));
        int offset = tryParse(req.getParameter("offset"));
        String nameOfMasterFilter = req.getParameter("mastersFilter");
        String compilationStatusFilter = req.getParameter("compilationStatusFilter");
        String paymentStatusFilter = req.getParameter("paymentStatusFilter");
        int amountRequestsInDB = ImpRequestDAO.getInstance().countAllRequest();
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        User user = ImpUserDAO.getInstance().getUserByLogin(login);

        if (prev != 0 && offset - prev >= 0) {
            offset -= prev;
        } else if (next != 0 && offset + next < amountRequestsInDB) {
            offset += next;
        }


        List<Request> userRequests = ImpRequestDAO.getInstance().getAllUsersRequestFilter(login, column, sortType, nameOfMasterFilter, compilationStatusFilter, paymentStatusFilter, 3, offset);
        Map<String, String> masterNames = ImpUserDAO.getInstance().getAllMasters();
        Map<Integer, String> compilationStatus = ImpCompilationStatusDAO.getInstance().getAllCompilationStatus();
        Map<Integer, String> paymentStatus = ImpPaymentStatusDAO.getInstance().getAllPaymentStatus();

        req.setAttribute("language", language);
        req.setAttribute("user", user);
        req.setAttribute("masterNames", masterNames);
        req.setAttribute("compilationStatus", compilationStatus);
        req.setAttribute("paymentStatus", paymentStatus);
        req.setAttribute("userRequests", userRequests);
        req.setAttribute("sortType", sortType);
        req.setAttribute("column", column);
        req.setAttribute("offset", offset);
        req.setAttribute("nameOfMasterFilter", nameOfMasterFilter);
        req.setAttribute("compilationStatusFilter", compilationStatusFilter);
        req.setAttribute("paymentStatusFilter", paymentStatusFilter);
        getServletContext().getRequestDispatcher("/user/profile.jsp").forward(req, resp);
    }

    public static int tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
