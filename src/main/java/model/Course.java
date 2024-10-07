package model;

public class Course {
    private int id;
    private String name;
    private int yhp;
    private String description;

    // Constructor with all parameters including id
    public Course(int id, String name, int yhp, String description) {
        this.id = id;
        this.name = name;
        this.yhp = yhp;
        this.description = description;
    }
/*
    // Constructor without id (useful if id is auto-generated)
    public Course(String name, int yhp, String description) {
        this.name = name;
        this.yhp = yhp;
        this.description = description;
    }*/

    // Getters and Setters
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
}
