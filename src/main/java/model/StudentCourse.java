package model;

public class StudentCourse {
    private final String studentName;
    private final String courseName;
    private final String courseDescription;
    private final String teacherName;

    public StudentCourse(String studentName, String courseName, String courseDescription, String teacherName) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
