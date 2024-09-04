package model;
/*

import java.io.Serializable;

*/
/**
 * User bean represents the user connected to the Grit Academy Portal
 *//*

public class Old_UserBean implements Serializable {


    //shoudl be set to the same is it is in the DB
    private String id;
    private UserType userType;
    private PrivilegeType privilegeType = PrivilegeType.user;
    private StateType stateType = StateType.anonymous;

    public Old_UserBean(){}
    public Old_UserBean(String id, UserType userType, PrivilegeType privilegeType, StateType stateType){
        this.id=id;
        this.userType=userType;
        this.privilegeType = privilegeType;
        this.stateType=stateType;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public void setPrivilageType(PrivilegeType privilegeType) {
        this.privilegeType = privilegeType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public PrivilegeType getPrivilegeType() {
        return privilegeType;
    }

    public StateType getStateType() {
        return stateType;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "userType: "+userType + " privilageType: " + privilegeType + " stateType: "  + stateType;
    }


}

*/


