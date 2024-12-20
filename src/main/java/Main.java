import model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();


        List<Course> courses = database.getAllCourses();
     //   List<Course> coursesforStudents = database.getCoursesForStudent(null);
        List<Student> students = database.getAllStudents();
        List<StudentCourse> studentCourses = database.getStudentsCoursesWithNames();
       // List<CourseDetails> courseDetails = database.getDetailedCoursesForStudent(null);


        // Print out each course to the console
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getName());
            System.out.println("YHP: " + course.getYhp());
            System.out.println("Description: " + course.getDescription());
            System.out.println("-------------------------------------");
        }

        for (Student student : students) {
            System.out.println("student ID: " + student.getId());
            System.out.println("First Name: " + student.getFname());
            System.out.println("Last name: " + student.getLname());
            System.out.println("Town: " + student.getTown());
            System.out.println("email: " + student.getEmail());
            System.out.println("phone number: " + student.getPhone());
            System.out.println("-------------------------------------");
        }
        for (StudentCourse studentCourse : studentCourses) {
            System.out.println("student name " + studentCourse.getStudentName());
            System.out.println("course name " + studentCourse.getCourseName());
            System.out.println("Description " + studentCourse.getCourseDescription());
            System.out.println("-------------------------------------");
        }
      //  System.out.println(database.getStudentsCourses1());
    //    System.out.println(database.getStudentsCoursesWithNames());
       // System.out.println(coursesforStudents);


    }
}
