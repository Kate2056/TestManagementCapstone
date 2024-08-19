package com.softwareengineering.testmanagementcapstone.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    private int userID;
    private String email;
    private String password;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }


    public String loginConfirm(){
        return "User logged in as: " + userID;
    }
}
