package com.kabaldin.controller.servlets;

import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        req.setAttribute("language", language);
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");

        User user = new User(login, email, password, firstName, lastName, phoneNumber);
        ImpUserDAO.getInstance().saveUser(user);

        req.getSession().setAttribute("user", user);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
