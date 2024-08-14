package com.softwareengineering.testmanagementcapstone.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admins")
public class Admin extends User{
    @PrimaryKey
    private int userID;
    private String adminCode;
    public String getAdminCode(){return adminCode;}
    public void setAdminCode(){this.adminCode = adminCode;}


    public Admin(int userID, String email, String password, String adminCode) {
        super(userID, email, password);
        this.adminCode  = adminCode;
    }


}
