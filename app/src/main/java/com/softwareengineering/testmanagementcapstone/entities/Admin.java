package com.softwareengineering.testmanagementcapstone.entities;

public class Admin extends User{
    private String adminCode;

    public Admin(int userID, String email, String password, String adminCode) {
        super(userID, email, password);
        this.adminCode  = adminCode;
    }
}
