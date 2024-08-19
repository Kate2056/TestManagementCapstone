package com.softwareengineering.testmanagementcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.softwareengineering.testmanagementcapstone.UI.AdminLogin;
import com.softwareengineering.testmanagementcapstone.UI.UserLogin;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.Report;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        repository = new Repository(getApplication());
        if(repository.getmAllReports().isEmpty()){
            Report r1 = new Report(1, "Passed");
            Report r2 = new Report(2, "Blocked");
            Report r3 = new Report(3, "Failed");
            repository.insert(r1);
            repository.insert(r2);
            repository.insert(r3);
        }



        Button userLogin = findViewById(R.id.userLoginButton);
        userLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, UserLogin.class);
                startActivity(intent);
            }
        });

        Button adminLogin = findViewById(R.id.adminLoginButton);
        adminLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addData) {
            User user = new User(1, "test@gmail.com", "123test");
            Admin admin = new Admin(1, "test@gmail.com", "12345678", "1234$");
            TestCase t1 = new TestCase(1, "User Login", "1. Open App 2. Enter username 3. Enter password 4. Click submit", "User should be able to login", "8/17/2024 16:23:14", "8/17/2024 16:23:14");
            TestCase t2 = new TestCase(2, "User Logout", "1. Open Menu 2. Click Logout", "User should be logged out", "8/17/2024 16:24:56", "8/17/2024 16:24:56");
            TestCase t3 = new TestCase(3, "Admin Login", "1. Open App 2. Enter username 3. Enter password 4. Enter Passcode 5. Click submit", "User should be able to login", "8/17/2024 16:27:32", "8/17/2024 16:27:32");
            TestCase t4 = new TestCase(4, "Admin Logout", "1. Open Menu 2. Click Logout", "User should be Logged out", "8/17/2024 16:31:42", "8/17/2024 16:31:42" );
            TestCase t5 = new TestCase(5, "Invalid Password", "1. Open App 2. Enter valid username 3. Enter incorrect password", "User should not be able to login", "8/17/2024 18:11:36", "8/17/2024 18:11:36" );


            TestResult r1 = new TestResult(1, "Passed", "8/17/2024 16:29:24", 1);
            TestResult r2 = new TestResult(2, "Blocked", "8/17/2024 16:31:41", 1);
            TestResult r3 = new TestResult(3, "Passed", "8/17/2024 16:35:21", 2);
            TestResult r4 = new TestResult(4, "Failed", "8/17/2024 16:37:35", 2);
            TestResult r5 = new TestResult(5, "Failed", "8/17/2024 16:48:56", 2);
            TestResult r6 = new TestResult(6, "Blocked", "8/17/2024 16:49:22", 3);
            TestResult r7 = new TestResult(7, "Passed", "8/17/2024 16:57:14", 3);
            TestResult r8 = new TestResult(8, "Passed", "8/17/2024 17:14:56", 4);
            TestResult r9 = new TestResult(9, "Blocked", "8/17/2024 17:15:22", 4);
            TestResult r10 = new TestResult(10, "Passed", "8/17/2024 17:17:14", 4);
            TestResult r11 = new TestResult(11, "Passed", "8/17/2024 17:42:43", 5);
            TestResult r12 = new TestResult(12, "Blocked", "8/17/2024 17:55:29", 5);
            TestResult r13 = new TestResult(13, "Passed", "8/17/2024 18:03:54", 5);

            List<TestCase> tests = new ArrayList<TestCase>();
            tests.add(t1);
            tests.add(t2);
            tests.add(t3);
            tests.add(t4);
            tests.add(t5);

            List<TestResult> results = new ArrayList<TestResult>();
            results.add(r1);
            results.add(r2);
            results.add(r3);
            results.add(r4);
            results.add(r5);
            results.add(r6);
            results.add(r7);
            results.add(r8);
            results.add(r9);
            results.add(r10);
            results.add(r11);
            results.add(r12);
            results.add(r13);
            boolean toAdd = true;
            repository = new Repository(getApplication());

            for(TestCase t : tests){
                repository.insert(t);

            }
            for(TestResult r : results) {
                repository.insert(r);
            }

            repository.insert(user);

            repository.insert(admin);
            Toast.makeText(MainActivity.this, "Sample Data Added!", Toast.LENGTH_LONG).show();

        }
        return true;
    }

}