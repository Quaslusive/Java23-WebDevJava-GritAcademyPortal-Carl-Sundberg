<h1>Grit Academy Portal</h1>


Technologies Used

    Backend: Java, Servlets, JDBC
    Frontend: JSP, HTML, CSS, JSTL (JavaServer Pages Standard Tag Library)
    Database: MySQL
    Application Server: Apache Tomcat



Database Schema
Tables

    Students
        id, fName, lName, town, email, phone, username, password, privilege_type

    Teachers
        id, fName, lName, town, email, phone, username, password, privilege_type

    Courses
        id, name, yhp, description

    Students_Courses
        id, students_id, courses_id

    Teachers_Courses
        id, teachers_id, courses_id

Setup Instructions
Prerequisites

    Java JDK 8 or later
    Apache Tomcat 9 or later
    MySQL 8.0
    IDE (e.g., IntelliJ IDEA, Eclipse)


Key Classes and Their Responsibilities
Model

    UserBean: Represents users (students/teachers) in the system.
    StudentCourse: Encapsulates data about students, courses, and teachers.
    Course: Holds course details like name, description, and YHP.

Database

    Handles all database interactions using JDBC.
    Key methods include:
        findUserByUsername(String username)
        getAllCourses()
        getAllStudents()
        getStudentsCoursesWithNames()

Servlets

    LoginServlet: Manages user authentication and session creation.
    UserPageServlet: Handles user-specific actions based on roles (Student, Teacher, Admin).
