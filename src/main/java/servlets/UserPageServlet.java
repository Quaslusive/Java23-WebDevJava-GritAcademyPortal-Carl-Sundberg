package servlets;

import model.Course;
import model.Database;
import model.UserBean;
import model.UserType;
import model.PrivilegeType;
import model.StateType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userPageServlet", urlPatterns = "/userpage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        // If user is not logged in or is anonymous, redirect to login page
        if (user == null || user.getUsername() == null || user.getStateType() != StateType.CONFIRMED) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Course> courses = null;
        try {
            // Initialize the Database instance
            Database db = new Database();

            // Fetch courses based on user type and privilege type
            if (user.getUserType() == UserType.STUDENT && user.getPrivilegeType() == PrivilegeType.USER) {
                // Student can only view their enrolled courses
                courses = db.getCoursesForStudent(user.getUsername());

            } else if (user.getUserType() == UserType.TEACHER && user.getPrivilegeType() == PrivilegeType.USER) {
                // Teacher with "USER" privilege can view only the courses they teach
                courses = db.getCoursesForTeacher(user.getUsername());

            } else if (user.getUserType() == UserType.TEACHER && user.getPrivilegeType() == PrivilegeType.ADMIN) {
                // Admin teacher can view all courses
                courses = db.getAllCourses();
            } else {
                throw new ServletException("Unknown user type or privilege type.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving courses");
            return;
        }

        // Set the courses as an attribute and forward to the UserPage.jsp
        request.setAttribute("coursesData", courses);
        request.getRequestDispatcher("/JSP/UserPage.jsp").forward(request, response);
    }
}
