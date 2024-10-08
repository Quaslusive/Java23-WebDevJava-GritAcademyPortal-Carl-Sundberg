package model;

public class Student {

    private int id;
    private String fname;
    private String lname;
    private String town;
    private String email;
    private String phone;

    public Student(int id, String fname, String lname, String town, String email, String phone){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.town = town;
        this.email = email;
        this.phone = phone;
    }


    public int getId() {

        return id;
    }
    public String getFname() {

        return fname;
    }
    public String getLname() {

        return lname;
    }
    public String getTown() {

        return town;
    }
    public String getEmail() {

        return email;
    }
    public String getPhone() {

        return phone;
    }

}
