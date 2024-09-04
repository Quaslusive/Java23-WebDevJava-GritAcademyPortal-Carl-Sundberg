package servlets;

import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet("/assignCourse")
public class AssignCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String teacherId = request.getParameter("teacherId");
        String courseId = request.getParameter("courseId");

        Database db = Database.getInstance();
        UserBean user = db.findUserByUsername(teacherId);

        if (user != null && user.getUserType() == UserType.TEACHER) {
            db.assignTeacherToCourse(Integer.parseInt(teacherId), Integer.parseInt(courseId));
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }


}

/*
@WebServlet("/AssignCourseServlet")
public class AssignCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Database db = Database.getInstance();
        boolean success = db.assignTeacherToCourse(teacherId, courseId);

        if (success) {
            response.sendRedirect("userPage.jsp");  // Redirect till en relevant sida efter att ha lyckats
        } else {
            response.sendRedirect("assignCourse.jsp?error=assign_failed");  // Visa ett felmeddelande
        }
    }*/

