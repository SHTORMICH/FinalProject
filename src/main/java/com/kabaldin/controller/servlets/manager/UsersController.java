package com.kabaldin.controller.servlets.manager;

import com.kabaldin.controller.DAO.ImpDAO.ImpRoleDAO;
import com.kabaldin.controller.DAO.ImpDAO.ImpUserDAO;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/manager/users")
public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        List<User> users = ImpUserDAO.getInstance().getAllUsers();
        Map<Integer, String> roles = ImpRoleDAO.getInstance().getAllRoles();
        req.setAttribute("roles", roles);
        req.setAttribute("users", users);
        req.setAttribute("language", language);
        getServletContext().getRequestDispatcher("/manager/users.jsp").forward(req, resp);

    }

}
