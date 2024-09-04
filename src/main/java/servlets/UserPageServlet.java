package servlets;

import model.Course;
import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class UserPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArrayList<Course> courses = new ArrayList<>();

        try {
            // Hämta anslutningen från servletens kontext
            Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

            if (conn == null) {
                throw new ServletException("Database connection not initialized properly.");
            }

            // Skapa en instans av Database med den hämtade anslutningen
            Database db = Database.getInstance();


            if (user.getUserType() == UserType.STUDENT) {
                courses = (ArrayList<Course>) db.getCoursesForStudent(user.getUsername());
            } else if (user.getUserType() == UserType.TEACHER) {
                courses = (ArrayList<Course>) db.getCoursesForTeacher(user.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/JSP/userPage.jsp").forward(request, response);
    }
}










/*

package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserPageServlet",  urlPatterns = "/userpage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
*/
