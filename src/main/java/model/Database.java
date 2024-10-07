package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    // JDBC URL, username, and password for MySQL database
    private static final String URL = "jdbc:mysql://localhost:3306/gritacademyportal";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Consider re-throwing as a RuntimeException
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Simplified method to find user by username
    public UserBean findUserByUsername(String username) {
        UserBean user = findUser(username, "Students", UserType.STUDENT);
        if (user == null) {
            user = findUser(username, "Teachers", UserType.TEACHER);
        }
        return user;
    }

    private UserBean findUser(String username, String tableName, UserType userType) {
        UserBean user = null;
        String query = "SELECT * FROM " + tableName + " WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserBean();
                    user.setId(rs.getInt("id")); // Set the user's ID from the database
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setUserType(userType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public List<Course> getCoursesForStudent(String username) {
        System.out.println("Fetching courses for student: " + username);
        return getCourses(username, "Students", "Students_Courses", "students_id");

    }


    public List<Course> getCoursesForTeacher(String username) {
        System.out.println("Fetching courses for teacher: " + username);
        return getCourses(username, "Teachers", "Teachers_Courses", "teachers_id");
    }


/*

    public List<Course> getCoursesForStudent(String username) {
        return getCourses(username, "Students", "Students_Courses", "students_id");
    }

    public List<Course> getCoursesForTeacher(String username) {
        return getCourses(username, "Teachers", "Teachers_Courses", "teachers_id");
    }
*/

    private List<Course> getCourses(String username, String userTable, String courseTable, String userIdColumn) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.name, c.description FROM Courses c " +
                "JOIN " + courseTable + " uc ON c.id = uc.courses_id " +
                "JOIN " + userTable + " u ON uc." + userIdColumn + " = u.id " +
                "WHERE u.username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(rs.getInt("id") ,rs.getString("name"), rs.getInt("yhp"), rs.getString("description")));
                }
            }
            System.out.println("Fetched " + courses.size() + " courses for student: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("yhp"),
                        rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Number of courses fetched: " + courses.size());

        return courses;
    }

    public void registerTeacher(String fName, String lName, String town, String email, String phone, String username, String password, String privilegeType) {
        String query = "INSERT INTO Teachers (fName, lName, town, email, phone, username, password, privilege_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, town);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, username);
            stmt.setString(7, password); // In production, hash the password!
            stmt.setString(8, privilegeType);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerStudent(String fName, String lName, String town, String email, String phone, String username, String password) {
        String query = "INSERT INTO Students (fName, lName, town, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, town);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, username);
            stmt.setString(7, password); // In production, hash the password!
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean assignTeacherToCourse(int teacherId, int courseId) {
        return assignToCourse("Teachers_Courses", "teachers_id", teacherId, courseId);
    }

    public boolean enrollStudentInCourse(int studentId, int courseId) {
        return assignToCourse("Students_Courses", "students_id", studentId, courseId);
    }

    private boolean assignToCourse(String tableName, String userIdColumn, int userId, int courseId) {
        String query = "INSERT INTO " + tableName + " (" + userIdColumn + ", courses_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/*public boolean addUser(UserBean user) {
    String query = "INSERT INTO Users (username, password, user_type) VALUES (?, ?, ?)";

    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());  // In production, hash the password!
        stmt.setString(3, user.getUserType().name());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;  // Return true if the user was successfully added
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
*/
    public boolean addUser(UserBean user) {
        String query;
        if (user.getUserType() == UserType.STUDENT) {
            query = "INSERT INTO Students (username, password) VALUES (?, ?)";
        } else if (user.getUserType() == UserType.TEACHER) {
            query = "INSERT INTO Teachers (username, password, privilege_type) VALUES (?, ?, ?)";
        } else {
            // Handle other user types if necessary
            return false;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In production, hash the password!
            if (user.getUserType() == UserType.TEACHER) {
                stmt.setString(3, "user"); // Or use user.getPrivilegeType()
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // No need for explicit close method since we're using try-with-resources
}
