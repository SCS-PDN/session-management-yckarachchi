package com.assignment4;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DashboardServlet extends HttpServlet {
    private static final List<Course> COURSES = Arrays.asList(
        new Course("101", "Web Programming", "Dr. Silva"),
        new Course("102", "Data Structures", "Dr. Perera"),
        new Course("103", "Database Systems", "Dr. Fernando")
    );

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.html?error=Please+login+first");
            return;
        }

        req.setAttribute("courses", COURSES);

        List<String> enrolled = (List<String>) session.getAttribute("enrolledCourses");
        if (enrolled == null) enrolled = new ArrayList<>();
        req.setAttribute("enrolledCourses", enrolled);

        String msg = req.getParameter("msg");
        if (msg != null) req.setAttribute("msg", msg);

        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, resp);
    }
}
