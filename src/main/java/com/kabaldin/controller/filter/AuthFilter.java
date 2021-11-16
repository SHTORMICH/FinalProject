package com.kabaldin.controller.filter;

import com.kabaldin.controller.DAO.DBManager;
import com.kabaldin.controller.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<DBManager> dao = (AtomicReference<DBManager>) request.getServletContext().getAttribute("DAO");

        final HttpSession session = request.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final User.AccessLevel role = (User.AccessLevel) session.getAttribute("role");

            moveToMenu(request, response, role);

        } else if (dao.get().userIsExist(login, password)) {
            final User.AccessLevel role =  dao.get().getUserByLoginAndPassword(login, password);

            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", role);

            moveToMenu(request, response, role);
        } else {

            moveToMenu(request, response, User.AccessLevel.USER);
        }
    }

    private void moveToMenu(HttpServletRequest request, HttpServletResponse response, User.AccessLevel role) throws ServletException, IOException {
        if (role.equals(User.AccessLevel.MANAGE)) {
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } else if (role.equals(User.AccessLevel.MASTER)) {
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        }
    }
}
