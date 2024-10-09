package servlets;

import model.Course;
import model.Teacher;
import model.Student;
import model.Database;
import model.UserBean;
import model.UserType;
import model.PrivilegeType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static model.PrivilegeType.*;

@WebServlet(name = "userPageServlet", urlPatterns = "/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);  // Använd sessionen, skapa inte en ny om den inte finns
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        UserBean user = (UserBean) session.getAttribute("user");

        try {
            Database db = new Database();

            if (user.getUserType() == UserType.STUDENT && user.getPrivilegeType() == USER) {
                db.getCoursesForStudent(user.getUsername());
                request.setAttribute("userType", "student");
                request.setAttribute("studentCourses", db.getCoursesForStudent(user.getUsername()));
                request.getRequestDispatcher("/JSP/studentPage.jsp").forward(request, response);

            } else if (user.getUserType() == UserType.TEACHER && user.getPrivilegeType() == USER) {
                db.getCoursesForTeacher(user.getUsername());
                request.setAttribute("userType", "teacher");
                request.setAttribute("teacherCourses", db.getCoursesForTeacher(user.getUsername()));
                request.getRequestDispatcher("/JSP/teacherPage.jsp").forward(request, response);

         /*   } else if (user.getUserType() == UserType.TEACHER && user.getPrivilegeType() == ADMIN) {
                db.getAllCourses();
                request.setAttribute("userType", "admin");
                request.setAttribute("courseData", db.getAllCourses());
                request.getRequestDispatcher("/JSP/adminPage.jsp").forward(request, response);

          */
            } else {
                request.setAttribute("error", "Guest are not allowed to peep on others stuff.");
                request.getRequestDispatcher("/JSP/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp?message=Error retrieving courses").forward(request, response);
        }
    }
}
