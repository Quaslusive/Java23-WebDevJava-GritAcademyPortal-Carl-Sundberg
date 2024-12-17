package servlets;

import model.Database;
import model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "coursesServlet", urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Database db = new Database();
        UserBean user = (UserBean) session.getAttribute("user");

        System.out.println("User type is: " + user.getUserType());
        request.setAttribute("courseData", db.getAllCourses());
        request.getRequestDispatcher("JSP/courses.jsp").forward(request, response);
    }

    }

