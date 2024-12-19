package model;

public class Course {
    private final int id;
    private final String name;
    private final int yhp;
    private final String description;


    public Course(int id, String name, int yhp, String description) {
        this.id = id;
        this.name = name;
        this.yhp = yhp;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYhp() {
        return yhp;
    }

    public String getDescription() {
        return description;
    }
}
