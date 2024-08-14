package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.softwareengineering.testmanagementcapstone.MainActivity;
import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.User;

public class HomePage extends AppCompatActivity {
    TextView userLoggedIn;
    int userID;
    Repository repository;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        repository = new Repository(getApplication());
        userID = getIntent().getIntExtra("user", -1);
        userLoggedIn = findViewById(R.id.userLoggedIn);

        for(User i : repository.getmAllUsers()){
            if(i.getUserID() == userID){
                currentUser = i;
            }
        }
        if(userID != -1){
            userLoggedIn.setText(currentUser.getEmail());
        }else{
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
        }



}}
