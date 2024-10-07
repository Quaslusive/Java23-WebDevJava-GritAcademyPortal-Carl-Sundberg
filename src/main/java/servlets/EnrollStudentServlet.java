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
import java.sql.Connection;

@WebServlet(name = "enrollStudentServlet", urlPatterns = "/enroll")
public class EnrollStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String courseId = request.getParameter("courseId");


        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        if (conn == null) {
            throw new ServletException("Database connection not initialized properly.");
        }


        Database db = new Database();


        UserBean user = db.findUserByUsername(username);


        if (user != null && user.getUserType() == UserType.STUDENT) {

            boolean success = db.enrollStudentInCourse(user.getId(), Integer.parseInt(courseId));

            if (success) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
*/
