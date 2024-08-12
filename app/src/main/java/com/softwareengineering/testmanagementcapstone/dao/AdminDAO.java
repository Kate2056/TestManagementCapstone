package com.softwareengineering.testmanagementcapstone.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.softwareengineering.testmanagementcapstone.entities.Admin;

import java.util.List;

@Dao
public interface AdminDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Admin admin);

    @Update
    void update(Admin admin);

    @Delete
    void delete(Admin admin);

    @Query("SELECT * FROM ADMINS ORDER BY userID ASC")
    List<Admin> getAllAdmins();
}
