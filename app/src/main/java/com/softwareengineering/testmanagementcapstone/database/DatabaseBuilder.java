package com.softwareengineering.testmanagementcapstone.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.softwareengineering.testmanagementcapstone.dao.AdminDAO;
import com.softwareengineering.testmanagementcapstone.dao.ReportDAO;
import com.softwareengineering.testmanagementcapstone.dao.TestCaseDAO;
import com.softwareengineering.testmanagementcapstone.dao.TestResultDAO;
import com.softwareengineering.testmanagementcapstone.dao.UserDAO;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.Report;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

@Database(entities = {User.class, Admin.class, TestCase.class, TestResult.class, Report.class}, version = 2, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract AdminDAO adminDAO();
    public abstract TestCaseDAO testCaseDAO();
    public abstract TestResultDAO testResultDAO();
    public abstract ReportDAO reportDAO();
    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized(DatabaseBuilder.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "TestManagementDB.db")
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
