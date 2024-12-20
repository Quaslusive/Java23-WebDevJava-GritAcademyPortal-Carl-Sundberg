package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/gritacademyportal";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public UserBean findUserByUsername(String username) {
        UserBean user = findUser(username, "Students", UserType.STUDENT);
        if (user == null) {
            user = findUser(username, "Teachers", UserType.TEACHER);
        }
        return user;
    }

    private UserBean findUser(String username, String tableName, UserType userType) {
       String query = "SELECT id, username, password, fName, lName, privilege_type FROM " + tableName + " WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserBean user = new UserBean();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFname(rs.getString("fName"));
                    user.setLname(rs.getString("lName"));
                    user.setUserType(userType);

                    String privilegeType = rs.getString("privilege_type");
                    try {
                        user.setPrivilegeType(PrivilegeType.valueOf(privilegeType.toUpperCase()));
                    } catch (IllegalArgumentException | NullPointerException e) {
                        user.setPrivilegeType(PrivilegeType.USER);
                    }
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding user by username: " + username);
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentCourse> getStudentsCoursesWithNames() {
        List<StudentCourse> studentCourses = new ArrayList<>();
        String query = "SELECT s.fName AS studentFirstName, s.lName AS studentLastName, " +
                "c.name AS courseName, c.description AS courseDescription, " +
                "t.fName AS teacherFirstName, t.lName AS teacherLastName " +
                "FROM students_courses sc " +
                "JOIN students s ON sc.students_id = s.id " +
                "JOIN courses c ON sc.courses_id = c.id " +
                "JOIN teachers_courses tc ON c.id = tc.courses_id " +
                "JOIN teachers t ON tc.teachers_id = t.id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String studentName = rs.getString("studentFirstName") + " " + rs.getString("studentLastName");
                String courseName = rs.getString("courseName");
                String courseDescription = rs.getString("courseDescription");
                String teacherName = rs.getString("teacherFirstName") + " " + rs.getString("teacherLastName");

                // Add the data to the StudentCourseBean
                studentCourses.add(new StudentCourse(studentName, courseName, courseDescription, teacherName));
            }
            System.out.println("getStudentsCoursesWithNames");
        } catch (SQLException e) {
            System.err.println("Error fetching students-courses with names and teachers.");
            e.printStackTrace();
        }
        return studentCourses;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("yhp"),
                        rs.getString("description")

                ));
            }
            System.out.println("getAllCourses");
        } catch (SQLException e) {
            System.err.println("Error fetching all courses.");
            e.printStackTrace();
        }
        return courses;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("town"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
            System.out.println("getAllStudents");
        } catch (SQLException e) {
            System.err.println("Error fetching all students.");
            e.printStackTrace();
        }
        return students;
    }
}