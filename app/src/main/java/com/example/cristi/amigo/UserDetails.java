package com.example.cristi.amigo;

/**
 * Created by cristi on 3/6/2018.
 */

public class UserDetails {

    public String userName;
    public String userDOB;
    public String userEmail;
    public String routesDone;

    public UserDetails(){
    }

    public UserDetails(String userName, String userDOB, String userEmail, String routesDone) {
        this.userName = userName;
        this.userDOB = userDOB;
        this.userEmail = userEmail;
        this.routesDone = routesDone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoutesDone(){
        return routesDone;
    }

    public  void setRoutesDone(String routesDone){
        this.routesDone = routesDone;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
