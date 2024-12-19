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
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/JSP/login.jsp");
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");
        Database db = new Database();


        System.out.println("UserPageServlet doPOST");
        List<StudentCourseBean> studentCourseData = db.getStudentsCoursesWithNames();
        // Debug: Log or print the fetched data
        System.out.println("Fetched Student-Course Data: " + studentCourseData);

        if (studentCourseData != null && !studentCourseData.isEmpty()) {
            for (StudentCourseBean record : studentCourseData) {
                System.out.println(record.getStudentName() + " - " + record.getCourseName() + " - " + record.getCourseDescription() + " - " + record.getTeacherName());
            }
        } else {
            System.out.println("No data retrieved from the database.");
        }
      /*  List<CourseDetails> detailedCourses = db.getDetailedCoursesForStudent(user.getUsername());
        // Debug: Log or print the fetched data
        System.out.println("Fetched Student-Course Data: " + detailedCourses);

        if (detailedCourses != null && !detailedCourses.isEmpty()) {
            for (CourseDetails record : detailedCourses) {
                System.out.println(record.getStudents() + " - " + record.getCourseName()+ " - " + record.getCourseId()+ " - " + record.getCourseDescription()+ " - " + record.getTeacherName());
            }
        } else {
            System.out.println("No data retrieved from the database.");
        }*/

/*
        List<Course> coursesForStudent = db.getCoursesForStudent(user.getUsername());
        // Debug: Log or print the fetched data
        System.out.println("Fetched Student-Course Data: " + coursesForStudent);

        if (coursesForStudent != null && !coursesForStudent.isEmpty()) {
            for (Course record : coursesForStudent) {
                System.out.println(record.getName() + " - " + record.getYhp()+ " - " + record.getDescription()+ " - " + record.getTeacherName());
            }
        } else {
            System.out.println("No data retrieved from the database.");
        }
*/

        String action = req.getParameter("action");


        try {
            if ("allStudents".equalsIgnoreCase(action)) {
                // Fetch all students and forward to studentsInfo.jsp
                req.setAttribute("allStudents", db.getAllStudents());
                req.getRequestDispatcher("/JSP/teachers/teacherUserPage.jsp").forward(req, resp);
            } else if ("viewAttendance".equalsIgnoreCase(action)) {
                // Forward to courseAttendance.jsp
                forwardToPage(req, resp, "/JSPs/Teachers/courseAttendance.jsp");

            } else if (req.getParameter("showStudents") != null) {
                // Fetch detailed courses or student-course data and forward to studentCourses.jsp
                req.setAttribute("studentCourses", db.getStudentsCoursesWithNames());
                forwardToPage(req, resp, "/JSP/students/studentCourses.jsp");

            } else if (req.getParameter("showStudentId") != null) {
                // Fetch and display course details for a specific student
                String studentId = req.getParameter("studentId");
                if (studentId != null && !studentId.isEmpty()) {
                    int id = Integer.parseInt(studentId);
                    req.setAttribute("courseData", db.getCoursesForStudent(user.getUsername()));
                    forwardToPage(req, resp, "/JSP/courses.jsp");
                } else {
                    System.out.println("Invalid student ID received.");
                    req.setAttribute("error", "Invalid student ID. Please try again.");
                    forwardToPage(req, resp, "/JSP/error.jsp");
                }

            } else if ("allCourses".equalsIgnoreCase(action)) {
                // Fetch all courses and forward to teacherUserPage.jsp
                req.setAttribute("allCourses", db.getAllCourses());
                forwardToPage(req, resp, "/JSP/teachers/teacherUserPage.jsp");

            } else {
                // Handle invalid actions
                System.out.println("Invalid action received: " + action);
                req.setAttribute("error", "Invalid action. Please try again.");
                req.getRequestDispatcher("/JSP/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            // Log and handle any exceptions
            log("Error processing POST request", e);
            req.setAttribute("error", "An error occurred while processing the request.");
            req.getRequestDispatcher("/JSP/error.jsp").forward(req, resp);
        }
    }
}
