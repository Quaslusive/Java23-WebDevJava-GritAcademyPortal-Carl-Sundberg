package model;

import java.io.Serializable;

public class UserBean implements Serializable {
    private int id;  // Add the ID field here
    private String username;
    private UserType userType;
    private StateType stateType;
    private String password;
    private PrivilegeType privilegeType;

    public UserBean() {

    }

    // Getter and Setter for 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters
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

    public PrivilegeType getPrivilegeType() {
        return privilegeType;

    }
}
