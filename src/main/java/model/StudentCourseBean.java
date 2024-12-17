package model;

public class StudentCourseBean {
    private String studentName;
    private String courseName;
    private String courseDescription;

    public StudentCourseBean(String studentName, String courseName, String courseDescription) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}