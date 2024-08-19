package com.softwareengineering.testmanagementcapstone.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.softwareengineering.testmanagementcapstone.entities.Report;

import java.util.List;

@Dao
public interface ReportDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Report report);

    @Update
    void update(Report report);

    @Delete
    void delete(Report report);

    @Query("SELECT * FROM REPORTS ORDER BY reportID ASC")
    List<Report> getAllReports();

}
