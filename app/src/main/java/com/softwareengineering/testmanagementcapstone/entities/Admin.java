package com.softwareengineering.testmanagementcapstone.entities;

import androidx.room.Entity;

@Entity(tableName = "admins")
public class Admin extends User{

    private String adminCode;
    public String getAdminCode(){return adminCode;}
    public void setAdminCode(){this.adminCode = adminCode;}


    public Admin(int userID, String email, String password, String adminCode) {
        super(userID, email, password);
        this.adminCode  = adminCode;
    }

    @Override
    public String loginConfirm(){
        return "Admin logged in as: " + getEmail();
    }
}
