import model.Course;
import model.Database;
import model.Student;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize the Database instance
        Database database = new Database();

        // Get the list of all courses
        List<Course> courses = database.getAllCourses();

        // Print out each course to the console
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getName());
            System.out.println("YHP: " + course.getYhp());
            System.out.println("Description: " + course.getDescription());
            System.out.println("-------------------------------------");
        }


    }
}
