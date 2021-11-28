package com.kabaldin.controller.servlets.master;

import com.kabaldin.controller.DAO.ImpDAO.ImpRequestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/master/refresh")
public class MasterRefreshController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int compilationStatus = Integer.parseInt(req.getParameter("compilationStatus"));
        int id = Integer.parseInt(req.getParameter("id"));
        ImpRequestDAO.getInstance().changeCompilationStatusInRequest(id, compilationStatus);
        resp.sendRedirect(req.getContextPath() + "/table/requests");
    }
}
