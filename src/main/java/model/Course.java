package model;

import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private int yhp;
    private String description;
    private String teacherName; // Teacher name included for join query

    // Default constructor
    public Course() {}

    // Constructor including teacher name
    public Course(int id, String name, int yhp, String description, String teacherName) {
        setId(id);
        setName(name);
        setYhp(yhp);
        setDescription(description);
        setTeacherName(teacherName);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public int getYhp() {
        return yhp;
    }

    public void setYhp(int yhp) {
        if (yhp < 0) {
            throw new IllegalArgumentException("YHP must be non-negative.");
        }
        this.yhp = yhp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        if (teacherName != null && teacherName.isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be empty.");
        }
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yhp=" + yhp +
                ", description='" + description + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


/*
package model;

public class Course {
    private int id;
    private String name;
    private int yhp;
    private String description;
    private String teacherName;


    public Course() {}


    public Course(int id, String name, int yhp, String teacherName, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
        this.yhp = yhp;
        this.description = description;
        this.teacherName = teacherName;
    }


    public Course(int id, String name, int yhp, String description) {
        this(id, name, yhp, null, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public int getYhp() {
        return yhp;
    }

    public void setYhp(int yhp) {
        if (yhp < 0) {
            throw new IllegalArgumentException("YHP must be non-negative.");
        }
        this.yhp = yhp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yhp=" + yhp +
                ", description='" + description + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
*/


/*
package model;

public class Course {
    private int id;
    private String name;
    private int yhp;
    private String description;
    private String teacherName;


    public Course(int id, String name, int yhp, String teacherName, String description) {
        this.id = id;
        this.name = name;
        this.yhp = yhp;
        this.description = description;
        this.teacherName = teacherName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYhp() {
        return yhp;
    }

    public void setYhp(int yhp) {
        this.yhp = yhp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
*/
