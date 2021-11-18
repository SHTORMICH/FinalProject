package com.kabaldin.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class AuthFilter implements Filter {
    private static final String LOGIN = "login";
    private final Set<String> allowedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls.add("/login");
        allowedUrls.add("/registration");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(LOGIN);
        String roleId = (String) session.getAttribute("role_id");
        if (login == null && !allowedUrls.contains(req.getServletPath())) {
            resp.sendRedirect("/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    /*@Override
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
    }*/
}
