package servlets;

import model.Course;
import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userPageServlet", urlPatterns = "/Userpage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        // If user is not logged in, redirect to the login page
        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Course> courses = new ArrayList<>();
        try {
            // Initialize the Database instance (without needing to pass a connection)
            Database db = new Database();

            // Fetch courses based on user type
            if (user.getUserType() == UserType.STUDENT) {
                courses = db.getCoursesForStudent(user.getUsername());
            } else if (user.getUserType() == UserType.TEACHER) {
                courses = db.getCoursesForTeacher(user.getUsername());
            } else {
                throw new ServletException("Unknown user type.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving courses");
            return;
        }

        // Set the courses as an attribute and forward to the UserPage.jsp
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/JSP/UserPage.jsp").forward(request, response);
    }
}
