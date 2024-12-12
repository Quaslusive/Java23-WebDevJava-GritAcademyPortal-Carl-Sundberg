package servlets;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet( "/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");
        Database db = new Database();

        try {

            switch (user.getUserType()) {
                case STUDENT:
                    if (user.getPrivilegeType() == PrivilegeType.USER) {
                        List<Course> studentCourses = db.getCoursesForStudent(user.getUsername());
                        request.setAttribute("studentCourses", studentCourses);
                        request.getRequestDispatcher("/JSP/UserPage.jsp").forward(request, response);


                    }
                    break;

                case TEACHER:
                    if (user.getPrivilegeType() == PrivilegeType.USER) {
                        List<Course> teacherCourses = db.getCoursesForTeacher(user.getUsername());
                        request.setAttribute("teacherCourses", teacherCourses);
                        request.getRequestDispatcher("/JSPUserPage.jsp").forward(request, response);
                  /*  } else if (user.getPrivilegeType() == PrivilegeType.ADMIN) {
                        List<Course> allCourses = db.getAllCourses();
                        request.setAttribute("allCourses", allCourses);
                        request.getRequestDispatcher("/JSP/AdminPage.jsp").forward(request, response);*/
                    }
                    break;

                default:
                    request.setAttribute("error", "Access denied for this user type.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp?message=Error retrieving data").forward(request, response);
        }
    }
}
