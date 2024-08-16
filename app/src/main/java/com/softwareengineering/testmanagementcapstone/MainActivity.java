package com.softwareengineering.testmanagementcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.softwareengineering.testmanagementcapstone.UI.AdminLogin;
import com.softwareengineering.testmanagementcapstone.UI.UserLogin;
import com.softwareengineering.testmanagementcapstone.database.Repository;

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
        //TestResult testResult = new TestResult(1, "Passed", "8/13/2024 12:43:46", 2);
        //TestResult test = new TestResult(2, "Passed", "8/15/2024 13:25:30", 2);
        //TestResult test1 = new TestResult(3, "Failed", "8/15/2024 13:36:09", 2);
        //repository = new Repository(getApplication());
       // repository.insert(test);
        //repository.insert(test1);
        //repository.insert(testResult);

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
}