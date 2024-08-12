package com.softwareengineering.testmanagementcapstone.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "testResults")
public class TestResult {
    @PrimaryKey
    private int resultID;
    private String result;
    private String dateTested;
    private int testID;

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDateTested() {
        return dateTested;
    }

    public void setDateTested(String dateTested) {
        this.dateTested = dateTested;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public TestResult(int resultID, String result, String dateTested, int testID) {
        this.resultID = resultID;
        this.result = result;
        this.dateTested = dateTested;
        this.testID = testID;
    }
}
