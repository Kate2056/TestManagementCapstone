package com.softwareengineering.testmanagementcapstone.database;

import android.app.Application;

import com.softwareengineering.testmanagementcapstone.dao.AdminDAO;
import com.softwareengineering.testmanagementcapstone.dao.TestCaseDAO;
import com.softwareengineering.testmanagementcapstone.dao.TestResultDAO;
import com.softwareengineering.testmanagementcapstone.dao.UserDAO;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AdminDAO mAdminDAO;
    private TestCaseDAO mTestCaseDAO;
    private TestResultDAO mTestResultDAO;
    private UserDAO mUserDAO;

    private List<Admin> mAllAdmins;
    private List<TestCase> mAllTestCases;
    private List<TestResult> mAllTestResults;
    private List<User> mAllUsers;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mAdminDAO = db.adminDAO();
        mTestCaseDAO = db.testCaseDAO();
        mTestResultDAO = db.testResultDAO();
        mUserDAO = db.userDAO();
    }

    public List<Admin> getmAllAdmins(){
        databaseExecutor.execute(()->{
            mAllAdmins = mAdminDAO.getAllAdmins();
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return mAllAdmins;
    }

    public void insert(Admin admin){
        databaseExecutor.execute(()->{
            mAdminDAO.insert(admin);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public void update(Admin admin){
        databaseExecutor.execute(()->{
            mAdminDAO.update(admin);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Admin admin){
        databaseExecutor.execute(()->{
            mAdminDAO.delete(admin);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<TestCase> getmAllTestCases(){
        databaseExecutor.execute(()->{
            mAllTestCases = mTestCaseDAO.getAllTestCases();
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return mAllTestCases;
    }

    public void insert(TestCase testCase){
        databaseExecutor.execute(()->{
            mTestCaseDAO.insert(testCase);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(TestCase testCase){
        databaseExecutor.execute(()->{
            mTestCaseDAO.update(testCase);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(TestCase testCase){
        databaseExecutor.execute(()->{
            mTestCaseDAO.delete(testCase);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<TestResult> getmAllTestResults(){
        databaseExecutor.execute(()->{
            mAllTestResults = mTestResultDAO.getAllTestResults();
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return mAllTestResults;
    }

    public void insert(TestResult testResult){
        databaseExecutor.execute(()->{
            mTestResultDAO.insert(testResult);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(TestResult testResult){
        databaseExecutor.execute(()->{
            mTestResultDAO.update(testResult);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(TestResult testResult){
        databaseExecutor.execute(()->{
            mTestResultDAO.delete(testResult);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<User> getmAllUsers(){
        databaseExecutor.execute(()->{
            mAllUsers = mUserDAO.getAllUsers();
        });

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return mAllUsers;
    }

    public void insert(User user){
        databaseExecutor.execute(()->{
            mUserDAO.insert(user);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(User user){
        databaseExecutor.execute(()->{
            mUserDAO.update(user);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(User user){
        databaseExecutor.execute(()->{
            mUserDAO.delete(user);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public List<TestResult> getRelatedResults(int testID) {
        databaseExecutor.execute(() -> {
            mAllTestResults = mTestResultDAO.getRelatedResults(testID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTestResults;
    }
}
