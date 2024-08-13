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
import com.softwareengineering.testmanagementcapstone.entities.TestCase;
import com.softwareengineering.testmanagementcapstone.entities.TestResult;
import com.softwareengineering.testmanagementcapstone.entities.User;

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
        User user = new User(1, "test@gmail.com", "123test");
        TestCase test = new TestCase(1, "test1", "1. do this 2. do that", "should match", "8/13/2024", "8/13/2024");
        TestResult testResult = new TestResult(1, "Passed", "8/13/2024", 1);
        repository = new Repository(getApplication());
        repository.insert(user);
        repository.insert(test);
        repository.insert(testResult);

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