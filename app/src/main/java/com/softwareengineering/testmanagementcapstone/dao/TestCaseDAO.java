package com.softwareengineering.testmanagementcapstone.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.softwareengineering.testmanagementcapstone.entities.TestCase;

import java.util.List;

@Dao
public interface TestCaseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TestCase testCase);

    @Update
    void update(TestCase testCase);

    @Delete
    void delete(TestCase testCase);

    @Query("SELECT * FROM TESTCASES ORDER BY testID ASC")
    List<TestCase> getAllTestCases();
}
