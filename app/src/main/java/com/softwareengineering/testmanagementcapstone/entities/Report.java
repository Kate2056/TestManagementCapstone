package com.softwareengineering.testmanagementcapstone.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reports")
public class Report {
    @PrimaryKey
    private int reportID;
    private String reportName;

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Report(int reportID, String reportName) {
        this.reportID = reportID;
        this.reportName = reportName;
    }
}
