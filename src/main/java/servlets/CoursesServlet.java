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

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Database db = Database.getInstance();
        List<Course> courses = null;

        if (user.getUserType() == UserType.STUDENT) {
            courses = db.getCoursesForStudent(user.getUsername());
        } else if (user.getUserType() == UserType.TEACHER) {
            courses = db.getCoursesForTeacher(user.getUsername());
        }

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/JSP/courses.jsp").forward(request, response);
    }
}
