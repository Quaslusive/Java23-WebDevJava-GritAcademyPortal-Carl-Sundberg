package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String query = "SELECT id, username, password, fName, lName FROM " + tableName + " WHERE username = ?";
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
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding user by username: " + username);
            e.printStackTrace();
        }
        return null;
    }

/*
    public List<Course> getCoursesTaughtByTeacher(String teacherUsername) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.id, c.name, c.yhp, c.description " +
                "FROM courses c " +
                "JOIN teachers_courses tc ON c.id = tc.courses_id " +
                "JOIN teachers t ON tc.teachers_id = t.id " +
                "WHERE t.username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, teacherUsername);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create Course object for each row
                    Course course = new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("yhp"),
                            rs.getString("description"),
                            null  // No teacherName is needed here as this is a teacher's own courses
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses for teacher: " + teacherUsername);
            e.printStackTrace();
        }
        return courses;
    }*/


    public List<StudentCourseBean> getStudentsCoursesWithNames() {
        List<StudentCourseBean> studentCourses = new ArrayList<>();
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
                studentCourses.add(new StudentCourseBean(studentName, courseName, courseDescription, teacherName));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students-courses with names and teachers.");
            e.printStackTrace();
        }
        return studentCourses;
    }


/*

    public List<StudentCourseBean> getStudentsCoursesWithNames() {
        List<StudentCourseBean> studentCourses = new ArrayList<>();
        String query = "SELECT s.fName, s.lName, c.name AS courseName, c.description " +
                "FROM students_courses sc " +
                "JOIN students s ON sc.students_id = s.id " +
                "JOIN courses c ON sc.courses_id = c.id";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                studentCourses.add(new StudentCourseBean(
                        rs.getString("fName") + " " + rs.getString("lName"),
                        rs.getString("courseName"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students-courses with names.");
            e.printStackTrace();
        }
        return studentCourses;
    }
*/

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
                        rs.getString("description"),
                        null
                ));
            }
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
        } catch (SQLException e) {
            System.err.println("Error fetching all students.");
            e.printStackTrace();
        }
        return students;
    }

    public List<Course> getCoursesForStudent(String username) {
        String query = "SELECT c.id, c.name, c.yhp, c.description, t.fName AS teacherName " +
                "FROM courses c " +
                "JOIN students_courses sc ON c.id = sc.courses_id " +
                "JOIN students s ON sc.students_id = s.id " +
                "JOIN teachers_courses tc ON c.id = tc.courses_id " +
                "JOIN teachers t ON tc.teachers_id = t.id " +
                "WHERE s.username = ?";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("yhp"),
                            rs.getString("description"),
                            rs.getString("teacherName")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching courses for student: " + username);
            e.printStackTrace();
        }
        return courses;
    }
}


/*

    public List<Course> getCoursesForStudent(String username) {
        System.out.println("Fetching courses for student: " + username);
        return getCourses(username, "students", "students_courses", "students_id");

    }

    public List<Course> getCoursesForTeacher(String username) {
        System.out.println("Fetching courses for teacher: " + username);
        return getCourses(username, "teachers", "teachers_courses", "teachers_id");
    }
*/

/*

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
                    courses.add(new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("yhp"),
                            rs.getString("description")));
                }
            }
            System.out.println("Fetched " + courses.size() + " courses for student: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
*/






/*

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
*/

/*

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
*/

/*

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
*/
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

/*

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
*/
/*



    public List<Course> getCoursesForTeacher(String username) {
        String query = "SELECT c.id, c.name, c.yhp, c.description, s.fName AS studentName " +
                "FROM courses c " +
                "JOIN teachers_courses tc ON c.id = tc.courses_id " +
                "JOIN teachers t ON tc.teachers_id = t.id " +
                "JOIN students_courses sc ON c.id = sc.courses_id " +
                "JOIN students s ON sc.students_id = s.id " +
                "WHERE t.username = ?";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("yhp"),
                            rs.getString("description"),
                            rs.getString("studentName")
                    );
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
*/




