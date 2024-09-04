package model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebListener
public class Database implements ServletContextListener {
    private Connection connection;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Load the JDBC driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gritacademyportal", "root", "");

            // Store the connection in the servlet context for later use
            sce.getServletContext().setAttribute("DBConnection", connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close the database connection when the application is shut down
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve the database connection
    public Connection getConnection() {
        return connection;
    }




/*

    public static UserBean verification(String username, String password, String type) {
        UserBean user = null;
        String sql = "";

        if (username.equalsIgnoreCase("GUEST") && password.isEmpty()) {
            user = new UserBean();
            user.setUsername("GUEST");
            user.setStateType(StateType.ANONYMOUS);
            user.setUserType(UserType.GUEST);
            return user;
        }

        if (type.equalsIgnoreCase("student")) {
            sql = "SELECT * FROM students WHERE username = ? AND password = ?";
        } else if (type.equalsIgnoreCase("teacher")) {
            sql = "SELECT * FROM teachers WHERE username = ? AND password = ?";
        }

        try (PreparedStatement ps = getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserBean();
                    user.setId(rs.getInt("id"));
                    user.setfName(rs.getString("fname"));
                    user.setlName(rs.getString("lname"));
                    user.setTown(rs.getString("town"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setUsername(rs.getString("username"));
                    user.setPw(rs.getString("password"));

                    try {
                        user.setUserType(String.valueOf(UserType.valueOf(rs.getString("privilege_type").toUpperCase())));
                        user.setStateType(StateType.CONFIRMED);
                    } catch (Exception ex) {
                        user.setUserType(String.valueOf(UserType.STUDENT));
                        user.setStateType(StateType.CONFIRMED);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

        return user;
    }
*/



    public UserBean findUserByUsername(String username) {
        UserBean user = findInStudents(username);
        if (user == null) {
            user = findInTeachers(username);
        }
        return user;
    }

    private UserBean findInStudents(String username) {
        UserBean user = null;
        String query = "SELECT * FROM Students WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserBean();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password")); // Eller en hash
                user.setUserType(UserType.STUDENT); // Sätt UserType till STUDENT
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private UserBean findInTeachers(String username) {
        UserBean user = null;
        String query = "SELECT * FROM Teachers WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserBean();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password")); // Eller en hash
                user.setUserType(UserType.TEACHER); // Sätt UserType till TEACHER
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }




    public List<Course> getCoursesForStudent(String username) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.name, c.description FROM Courses c " +
                "JOIN Students_Courses sc ON c.id = sc.courses_id " +
                "JOIN Students s ON sc.students_id = s.id " +
                "WHERE s.username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(rs.getString("name"), rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getCoursesForTeacher(String username) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.name, c.description FROM Courses c " +
                "JOIN Teachers_Courses tc ON c.id = tc.courses_id " +
                "JOIN Teachers t ON tc.teachers_id = t.id " +
                "WHERE t.username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(rs.getString("name"), rs.getString("description")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void registerTeacher(String fName, String lName, String town, String email, String phone, String username, String password, String privilegeType) {
        String query = "INSERT INTO Teachers (fName, lName, town, email, phone, username, password, privilege_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, town);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, username);
            stmt.setString(7, password);
            stmt.setString(8, privilegeType);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(String fName, String lName, String town, String email, String phone, String username, String password) {
        String query = "INSERT INTO Students (fName, lName, town, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, town);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, username);
            stmt.setString(7, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM Teachers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFName(rs.getString("fName"));
                teacher.setLName(rs.getString("lName"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public boolean assignTeacherToCourse(int teacherId, int courseId) {
        String query = "INSERT INTO Teachers_Courses (teachers_id, courses_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, teacherId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enrollStudentInCourse(int studentId, int courseId) {
        String query = "INSERT INTO Students_Courses (students_id, courses_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Glöm inte att stänga anslutningen när databasklassen inte längre behövs
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
