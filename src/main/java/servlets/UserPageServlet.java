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

@WebServlet(name = "userPageServlet", urlPatterns = "/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UserPageServlet doGet");
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("UserPageServlet doPost");

        HttpSession session = request.getSession(false);

     /*   if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }*/

        UserBean user = (UserBean) session.getAttribute("user");
        Database db = new Database();

        try {

            switch (user.getUserType()) {
                case STUDENT:
                    if (user.getPrivilegeType() == PrivilegeType.USER) {
                        List<Course> studentCourses = db.getCoursesForStudent(user.getUsername());
                        request.setAttribute("studentCourses", studentCourses);
                        request.getRequestDispatcher("/JSP/StudentUserPage.jsp").forward(request, response);
                    }
                    break;

                case TEACHER:
/*
                    if (user.getPrivilegeType() == PrivilegeType.USER) {
*/
                        // Fetch all students and student-course data
                        List<Student> allStudents = db.getAllStudents();
/*
                        List<String> studentCoursesWithNames = db.getStudentsCoursesWithNames();
*/

                        // Set attributes for the JSP
                        request.setAttribute("allStudents", allStudents);
                        request.setAttribute("studentCourses", db.getStudentsCoursesWithNames());

                        // Forward to the teacher user page
                        request.getRequestDispatcher("/JSP/teachers/TeacherUserPage.jsp").forward(request, response);
System.out.println("All students " + allStudents + "lololo");
                   /* } else if (user.getPrivilegeType() == PrivilegeType.ADMIN) {*/
                        // Fetch all courses for admin
                        List<Course> allCourses = db.getAllCourses();

                        // Set attribute and forward to admin page
                        request.setAttribute("allCourses", allCourses);
                        request.getRequestDispatcher("/JSP/AdminPage.jsp").forward(request, response);
                   /* }*/
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
