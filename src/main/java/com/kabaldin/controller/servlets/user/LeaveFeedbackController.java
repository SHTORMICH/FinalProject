package com.kabaldin.controller.servlets.user;

import com.kabaldin.controller.DAO.ImpDAO.ImpFeedbackDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/leave/feedback")
public class LeaveFeedbackController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        String id = req.getParameter("id");
        String loginMaster = req.getParameter("loginMaster");
        req.setAttribute("language", language);
        req.setAttribute("id", id);
        req.setAttribute("loginMaster", loginMaster);
        req.getRequestDispatcher("/user/feedback.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        String loginMaster = req.getParameter("loginMaster");
        String fullNameMaster = ImpUserDAO.getInstance().getMasterByLogin(loginMaster);
        String feedback = req.getParameter("feedback");
        ImpFeedbackDAO.getInstance().addFeedback(id, fullNameMaster, feedback);
        resp.sendRedirect(req.getContextPath() + "/user/profile");
    }
}
