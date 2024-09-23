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

@WebServlet(name = "assignCourseServlet", urlPatterns = "/assignCourse")
public class AssignCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String teacherId = request.getParameter("teacherId");
        String courseId = request.getParameter("courseId");

        // Retrieve the connection from the servlet context
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        if (conn == null) {
            throw new ServletException("Database connection not initialized properly.");
        }

        // Create a Database instance using the connection
        Database db = new Database(conn);

        // Find the teacher by their username (teacherId) and check their user type
        UserBean user = db.findUserByUsername(teacherId);

        if (user != null && user.getUserType() == UserType.TEACHER) {
            // Assign the teacher to the course
            boolean success = db.assignTeacherToCourse(Integer.parseInt(teacherId), Integer.parseInt(courseId));

            if (success) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
