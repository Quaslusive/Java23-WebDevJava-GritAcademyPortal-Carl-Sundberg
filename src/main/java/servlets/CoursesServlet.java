package servlets;


import model.Course;
import model.Database;
import model.UserBean;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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


/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UserBean user = (UserBean) session.getAttribute("user");

        // Check if the user is logged in
        if (user == null || user.getUsername() == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Database db = new Database();
        List<Course> courses =db.getAllCourses();



        try {
            // Initialize the Database instance
            db = new Database();

            // Fetch the courses based on the user's type
            if (user.getUserType() == UserType.STUDENT) {
                courses = db.getCoursesForStudent(user.getUsername());
            } else if (user.getUserType() == UserType.TEACHER) {
                courses = db.getCoursesForTeacher(user.getUsername());
            } else {
                throw new ServletException("Invalid user type.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving courses");
            return;


        // Set courses as a request attribute and forward to the courses.jsp
        request.setAttribute("courseData", courses);
      //  request.getRequestDispatcher("/JSP/Fragments/courseListFragment.jsp").forward(request, response);
        request.getRequestDispatcher("/JSP/courses.jsp").forward(request, response);
        System.out.println( request.getAttribute("courseData") );
    }*/
    }

