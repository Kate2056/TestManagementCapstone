package com.softwareengineering.testmanagementcapstone.Tests;

import static org.junit.Assert.assertEquals;

import com.softwareengineering.testmanagementcapstone.entities.User;

import org.junit.Test;

public class UserLoginTest{
    @Test
    public void userLoginConfirm(){
        User user = new User(1, "userEmail", "123");
        assertEquals("User logged in as: userEmail", user.loginConfirm());
    }
}