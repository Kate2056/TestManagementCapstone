package com.softwareengineering.testmanagementcapstone.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.softwareengineering.testmanagementcapstone.entities.TestResult;

import java.util.List;

@Dao
public interface TestResultDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TestResult testResult);

    @Update
    void update(TestResult testResult);

    @Delete
    void delete(TestResult testResult);

    @Query("SELECT * FROM TESTRESULTS ORDER BY testID ASC")
    List<TestResult> getAllTestResults();

}
