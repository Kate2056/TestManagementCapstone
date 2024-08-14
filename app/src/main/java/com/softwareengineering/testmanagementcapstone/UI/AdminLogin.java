package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.User;

public class AdminLogin extends AppCompatActivity {
    Repository repository;
    String email;
    String password;
    String passcode;
    int userID;
    EditText editEmail;
    EditText editPassword;
    EditText editPasscode;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.adminEmailAddress);
        editPassword = findViewById(R.id.adminPassword);
        editPasscode = findViewById(R.id.adminPasscode);
        email = getIntent().getStringExtra("email");
        userID = getIntent().getIntExtra("id", -1);
        password = getIntent().getStringExtra("password");
        password = getIntent().getStringExtra("passcode");
        editEmail.setText(email);
        editPassword.setText(password);
        editPasscode.setText(passcode);
        repository = new Repository(getApplication());

        Button newAdmin = findViewById(R.id.newAdminButton);
        newAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AdminLogin.this, NewAdmin.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(repository.getmAllUsers().isEmpty()){
                    Toast.makeText(AdminLogin.this, "Please create an admin to login.", Toast.LENGTH_LONG).show();
                }
                else if(editEmail.getText().toString() != null && !editEmail.getText().toString().isEmpty() && editPassword.getText().toString() != null && !editPassword.getText().toString().isEmpty() && editPasscode.getText().toString() != null && !editPasscode.getText().toString().isEmpty()){
                    for(Admin i : repository.getmAllAdmins()){
                        String e = editEmail.getText().toString();
                        String p = editPassword.getText().toString();
                        String c = editPasscode.getText().toString();

                        if(e.equals(i.getEmail()) && p.equals(i.getPassword()) && c.equals(i.getAdminCode())){
                            Intent intent = new Intent(AdminLogin.this, HomePage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(AdminLogin.this, "Incorrect email or password.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

}
