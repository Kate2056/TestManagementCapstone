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
import com.softwareengineering.testmanagementcapstone.entities.Admin;

public class AdminHomePage extends AppCompatActivity {
    TextView adminLoggedIn;
    int adminID;
    Repository repository;
    Admin currentAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        repository = new Repository(getApplication());

        adminID = getIntent().getIntExtra("admin", -1);
        adminLoggedIn = findViewById(R.id.adminLoggedIn);
        for(Admin i : repository.getmAllAdmins()){
            if(i.getUserID() == adminID){
                currentAdmin = i;
            }
        }
        if(adminID != -1){
            adminLoggedIn.setText(currentAdmin.getEmail());
        }else{
            Intent intent = new Intent(AdminHomePage.this, MainActivity.class);
            startActivity(intent);
        }

}}
