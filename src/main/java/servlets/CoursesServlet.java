package servlets;

import model.Database;
import model.Course;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "coursesServlet", urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        // Check if the user is logged in
        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Course> courses;

        try {
            // Initialize the Database instance (no need for connection from ServletContext)
            Database db = new Database();

            // Fetch the courses based on the user's type
            if (user.getUserType() == UserType.STUDENT) {
                courses = db.getCoursesForStudent(user.getUsername());
            } else if (user.getUserType() == UserType.TEACHER) {
                courses = db.getCoursesForTeacher(user.getUsername());
            } else {
                throw new ServletException("Invalid user type.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving courses");
            return;
        }

        // Set courses as a request attribute and forward to the courses.jsp
        request.setAttribute("coursesData", courses);
        request.getRequestDispatcher("JSP/courses.jsp").forward(request, response);
    }
}
