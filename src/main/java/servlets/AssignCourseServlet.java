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

        String teacherUsername = request.getParameter("teacherId");  // Username of the teacher
        String courseId = request.getParameter("courseId");  // Course ID

        // Initialize the Database
        Database db = new Database();  // No need to pass Connection from ServletContext

        // Find the teacher by their username
        UserBean user = db.findUserByUsername(teacherUsername);

        // Check if the user exists and if they are a teacher
        if (user != null && user.getUserType() == UserType.TEACHER) {
            // Assign the teacher to the course using the teacher's ID from UserBean
            boolean success = db.assignTeacherToCourse(user.getId(), Integer.parseInt(courseId));

            // Redirect based on success or failure
            if (success) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp?message=Assignment failed");
            }
        } else {
            // Handle error if user is not found or is not a teacher
            response.sendRedirect("error.jsp?message=Invalid teacher");
        }
    }
}
