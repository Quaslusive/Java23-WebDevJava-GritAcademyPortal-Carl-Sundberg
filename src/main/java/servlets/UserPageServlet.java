package servlets;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UserPageServlet doGet");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");

        try {
            switch (user.getUserType()) {
                case STUDENT:
                    request.getRequestDispatcher("/JSP/students/studentUserPage.jsp").forward(request, response);
                    break;

                case TEACHER:
                    request.getRequestDispatcher("/JSP/teachers/teacherUserPage.jsp").forward(request, response);
                    break;

                default:
                    response.sendRedirect("login.jsp");
                    System.err.println("Access denied for this user type.");
                    break;
            }
        } catch (Exception e) {
            log("Error in UserPageServlet", e);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Database db = new Database();
        String action = req.getParameter("action");

        try {
            if ("allStudents".equalsIgnoreCase(action)) {
                req.setAttribute("allStudents", db.getAllStudents());
                req.getRequestDispatcher("/JSP/teachers/teacherUserPage.jsp").forward(req, resp);

            } else if ("allCourses".equalsIgnoreCase(action)) {
                req.setAttribute("allCourses", db.getAllCourses());
                req.getRequestDispatcher("/JSP/teachers/teacherUserPage.jsp").forward(req, resp);

            } else if ("viewStudents".equalsIgnoreCase(action)) {
                req.setAttribute("allStudents", db.getAllStudents());
                req.getRequestDispatcher("/JSP/admin/teacherAdminPage.jsp").forward(req, resp);

            } else if ("viewCourses".equalsIgnoreCase(action)) {
                req.setAttribute("allCourses", db.getAllCourses());
                req.getRequestDispatcher("/JSP/admin/teacherAdminPage.jsp").forward(req, resp);

            } else if ("viewEnrollment".equalsIgnoreCase(action)) {
                req.setAttribute("studentCourses", db.getStudentsCoursesWithNames());
                req.getRequestDispatcher("/JSP/admin/teacherAdminPage.jsp").forward(req, resp);

            } else if ("viewStudentsEnrollment".equalsIgnoreCase(action)) {
                req.setAttribute("studentCourses", db.getStudentsCoursesWithNames());
                req.getRequestDispatcher("/JSP/students/studentUserPage.jsp").forward(req, resp);

            }
        } catch (Exception e) {
            log("Error processing POST request", e);
            req.setAttribute("error", "An error occurred while processing the request.");
            req.getRequestDispatcher("/JSP/Error/error.jsp").forward(req, resp);
        }
        System.out.println("UserPageServlet doPOST");
    }
}
