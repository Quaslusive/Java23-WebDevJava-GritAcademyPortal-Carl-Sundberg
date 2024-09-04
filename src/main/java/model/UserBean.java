package model;

public class UserBean {
    private String username;
    private UserType userType;
    private StateType stateType;
    private String password; // Om du vill inkludera lösenordet

    // Getters och Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public StateType getStateType() {
        return stateType;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}








/*

import java.io.Serializable;

public class UserBean implements Serializable {
    private int id;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private String privilegeType;  // user, admin
    private String userType;       // student, teacher

    // Standardkonstruktör
    public UserBean() {}

    // Konstruktör med parametrar
    public UserBean(int id, String fName, String lName, String username, String password, String privilegeType, String userType) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.privilegeType = privilegeType;
        this.userType = userType;
    }

    // Getters och Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(String privilegeType) {
        this.privilegeType = privilegeType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Ytterligare metoder, t.ex. autentisering
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
*/
