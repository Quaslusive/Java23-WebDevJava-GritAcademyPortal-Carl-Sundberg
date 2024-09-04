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

@WebServlet("/enroll")
public class EnrollStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String courseId = request.getParameter("courseId");

        Database db = Database.getInstance();
        UserBean user = db.findUserByUsername(username);

        if (user != null && user.getUserType() == UserType.STUDENT) {
            db.enrollStudentInCourse(Integer.parseInt(username), Integer.parseInt(courseId));
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
