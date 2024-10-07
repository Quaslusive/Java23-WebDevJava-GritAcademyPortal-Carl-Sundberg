/*
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

@WebServlet( urlPatterns = "/assignCourse")
public class AssignCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String teacherUsername = request.getParameter("teacherId");
        String courseId = request.getParameter("courseId");

        Database db = new Database();
        UserBean user = db.findUserByUsername(teacherUsername);

        if (user != null && user.getUserType() == UserType.TEACHER) {
            boolean success = db.assignTeacherToCourse(user.getId(), Integer.parseInt(courseId));

            if (success) {
                response.sendRedirect("assignCourse.jsp");
            } else {
                response.sendRedirect("error.jsp?message=Assignment failed");
            }
        } else {
            response.sendRedirect("error.jsp?message=Invalid teacher");
        }
    }
}
*/
