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

@WebServlet(urlPatterns = "/userPage")
public class UserPageServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR_PAGE = "error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
System.out.println("UserPageServlet doGet");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(LOGIN_PAGE);
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");
        Database db = new Database();

        try {
            switch (user.getUserType()) {
                case STUDENT:

                    List<StudentCourseBean> studentCourses = db.getStudentsCoursesWithNames();
                    request.setAttribute("studentCourses", studentCourses);

                    // Forward to JSP
                    request.getRequestDispatcher("/JSP/students/studentUserPage.jsp").forward(request, response);



           /*         List<Course> studentCourses = db.getCoursesWithTeacherForStudent(user.getUsername());
                    request.setAttribute("courseData", studentCourses);

                    // Add debugging data
                    request.setAttribute("debugUser", user);
                    request.setAttribute("debugCourses", studentCourses);

                    // Forward to debug.jsp
                    request.getRequestDispatcher("/JSP/debug.jsp").forward(request, response);*/

                    handleStudentUser(request, response, db, user);
                    break;

                case TEACHER:
                    handleTeacherUser(request, response, db);
                    break;

                default:
                    forwardWithError(request, response, "Access denied for this user type.");
                    break;
            }
        } catch (Exception e) {
            log("Error in UserPageServlet", e);
            forwardWithError(request, response, "Error retrieving data.");
        }
    }

    private void handleStudentUser(HttpServletRequest request, HttpServletResponse response, Database db, UserBean user)
            throws ServletException, IOException {
        List<Course> studentCourses = db.getAllCourses();
       // List<String> studentCourses = db.getStudentsCoursesWithNames();
        request.setAttribute("studentCourses", studentCourses);

        // Forward to JSP
        request.getRequestDispatcher("/JSP/students/studentUserPage.jsp").forward(request, response);
       // request.getRequestDispatcher("/JSP/students/students.jsp").forward(request, response);



      //  request.setAttribute("courseData", db.getCoursesForStudent(user.getUsername()));
      //  forwardToPage(request, response, "/JSP/students/studentUserPage.jsp");
    }

    private void handleTeacherUser(HttpServletRequest request, HttpServletResponse response, Database db)
            throws ServletException, IOException {
        request.setAttribute("allStudents", db.getAllStudents());
        request.setAttribute("studentCourses", db.getStudentsCoursesWithNames());
        forwardToPage(request, response, "/JSP/teachers/teacherUserPage.jsp");
    }

    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("error", errorMessage);
        forwardToPage(request, response, LOGIN_PAGE);
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Database db = new Database();
        System.out.println("UserPageServlet doPOST");

        List<StudentCourseBean> studentCourseData = db.getStudentsCoursesWithNames();

        // Debug: Log or print the fetched data
        System.out.println("Fetched Student-Course Data: " + studentCourseData);

        if (studentCourseData != null && !studentCourseData.isEmpty()) {
            for (StudentCourseBean record : studentCourseData) {
                System.out.println(record.getStudentName() + " - " + record.getCourseName());
            }
        } else {
            System.out.println("No data retrieved from the database.");
        }
        String action = req.getParameter("action");

        try {
            if ("allStudents".equalsIgnoreCase(action)) {
                forwardToPage(req, resp, "/JSPs/Teachers/studentsInfo.jsp");
            } else if ("viewAttendance".equalsIgnoreCase(action)) {
                forwardToPage(req, resp, "/JSPs/Teachers/courseAttendance.jsp");
            } else if (req.getParameter("showStudents") != null) {
                req.setAttribute("studentCourses", db.getStudentsCoursesWithNames());
              //  req.setAttribute("studentData", db.getStudentsCoursesWithNames());
                forwardToPage(req, resp, "/JSP/students/studentCourses.jsp");
          //      forwardToPage(req, resp, "/JSPs/Students/StudentClassMates.jsp");
            } else if (req.getParameter("showStudentId") != null) {
                String studentId = req.getParameter("studentId");
//                req.setAttribute("courseData", db.getCoursesForStudentId(Integer.parseInt(studentId)));
                forwardToPage(req, resp, "/JSPs/courses.jsp");
            } else {
                forwardWithError(req, resp, "Invalid action.");
            }
        } catch (Exception e) {
            log("Error processing POST request", e);
            forwardWithError(req, resp, "An error occurred while processing the request.");
        }
    }
}
