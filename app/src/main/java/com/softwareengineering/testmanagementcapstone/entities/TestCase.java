package com.softwareengineering.testmanagementcapstone.entities;

public class TestCase {
    private int testID;
    private String name;
    private String steps;
    private String expectedResult;
    private String dateCreated;
    private String dateUpdated;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public TestCase(int testID, String name, String steps, String expectedResult, String dateCreated, String dateUpdated){
        this.testID = testID;
        this.name = name;
        this.steps = steps;
        this.expectedResult = expectedResult;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }
}
