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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userPageServlet", urlPatterns = "/userpage")
public class UserPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Course> courses = new ArrayList<>();

        try {
            // Retrieve the database connection from the servlet context
            Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

            if (conn == null) {
                throw new ServletException("Database connection not initialized properly.");
            }

            // Create a Database instance with the connection
            Database db = new Database(conn);

            // Fetch courses based on user type
            if (user.getUserType() == UserType.STUDENT) {
                courses = db.getCoursesForStudent(user.getUsername());
            } else if (user.getUserType() == UserType.TEACHER) {
                courses = db.getCoursesForTeacher(user.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page if an exception occurs
            return;
        }

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/JSP/userPage.jsp").forward(request, response);
    }
}
