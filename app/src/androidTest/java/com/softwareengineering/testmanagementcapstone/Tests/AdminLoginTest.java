package com.softwareengineering.testmanagementcapstone.Tests;

import static org.junit.Assert.assertEquals;

import com.softwareengineering.testmanagementcapstone.entities.Admin;


import org.junit.Test;

public class AdminLoginTest{
    @Test
    public void adminLoginConfirm(){
        Admin admin = new Admin(1, "adminEmail", "123", "1234$");
        assertEquals("Admin logged in as: adminEmail", admin.loginConfirm());
    }
}