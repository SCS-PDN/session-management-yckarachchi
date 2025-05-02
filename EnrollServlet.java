package com.assignment4;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.html?error=Please+login+first");
            return;
        }
        String courseId = req.getParameter("courseId");
        if (courseId != null) {
            List<String> enrolled = (List<String>) session.getAttribute("enrolledCourses");
            if (enrolled == null) enrolled = new ArrayList<>();
            if (!enrolled.contains(courseId)) {
                enrolled.add(courseId);
                session.setAttribute("enrolledCourses", enrolled);
            }
        }
        resp.sendRedirect("DashboardServlet?msg=Enrolled+successfully");
    }
}
