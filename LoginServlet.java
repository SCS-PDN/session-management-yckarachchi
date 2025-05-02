package com.assignment4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private static final Map<String, String> USERS = new HashMap<>();
    static {
        USERS.put("student1", "pass1");
        USERS.put("student2", "pass2");
        USERS.put("admin", "admin123");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && password.equals(USERS.get(username))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // 1 hour
            resp.addCookie(userCookie);

            resp.sendRedirect("DashboardServlet");
        } else {
            resp.sendRedirect("login.html?error=Invalid+credentials");
        }
    }
}
