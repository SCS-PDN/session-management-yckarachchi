<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.*, com.assignment4.Course" %>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    List<Course> courses = (List<Course>) request.getAttribute("courses");
    List<String> enrolledCourses = (List<String>) request.getAttribute("enrolledCourses");
    String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= username %>!</h2>
    <form action="LogoutServlet" method="get">
        <input type="submit" value="Logout">
    </form>
    <hr>
    <% if (msg != null) { %>
        <p style="color:green;"><%= msg %></p>
    <% } %>
    <h3>Available Courses</h3>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Instructor</th><th>Action</th>
        </tr>
        <% if (courses != null) {
            for (Course c : courses) { %>
            <tr>
                <td><%= c.getCourseId() %></td>
                <td><%= c.getCourseName() %></td>
                <td><%= c.getInstructor() %></td>
                <td>
                    <% if (enrolledCourses != null && enrolledCourses.contains(c.getCourseId())) { %>
                        Enrolled
                    <% } else { %>
                        <a href="EnrollServlet?courseId=<%= c.getCourseId() %>">Enroll</a>
                    <% } %>
                </td>
            </tr>
        <%   }
           } %>
    </table>
    <h3>My Enrolled Courses</h3>
    <ul>
        <% if (courses != null && enrolledCourses != null) {
            for (Course c : courses) {
                if (enrolledCourses.contains(c.getCourseId())) { %>
                    <li><%= c.getCourseName() %> (ID: <%= c.getCourseId() %>)</li>
        <%      }
            }
           } %>
    </ul>
</body>
</html>

