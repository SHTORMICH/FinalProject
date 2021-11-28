package com.kabaldin.controller.servlets.manager;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;
import com.kabaldin.controller.DAO.entity.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager/search")
public class SearchController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameOfMaster = req.getParameter("masters");
        int compilationStatus = Integer.parseInt(req.getParameter("compilationStatus"));
        int paymentStatus = Integer.parseInt(req.getParameter("paymentStatus"));
        //List<Request> allUsersRequestFilter = ImpRequestDAO.getInstance().getAllUsersRequestFilter(nameOfMaster, compilationStatus, paymentStatus);

        resp.sendRedirect(req.getContextPath() + "/manager/users/requests");
    }
}
