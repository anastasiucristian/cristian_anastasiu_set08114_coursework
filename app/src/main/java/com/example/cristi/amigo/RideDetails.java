package com.example.cristi.amigo;

/**
 * Created by cristi on 3/7/2018.
 */

public class RideDetails {
    public String userID;
    public String startPoint;
    public String endPoint;
    public String rideDate;
    public String rideTime;
    public String seats;

    public RideDetails(){
    }

    public RideDetails(String userID, String startPoint, String endPoint, String rideDate, String rideTime, String seats) {
        this.userID = userID;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.rideDate = rideDate;
        this.rideTime = rideTime;
        this.seats = seats;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRideDate() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate = rideDate;
    }

    public String getRideTime() {
        return rideTime;
    }

    public void setRideTime(String rideTime) {
        this.rideTime = rideTime;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
