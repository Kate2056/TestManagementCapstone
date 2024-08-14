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
import com.softwareengineering.testmanagementcapstone.entities.User;

public class UserLogin extends AppCompatActivity {
    Repository repository;
    String email;
    String password;
    int userID;
    EditText editEmail;
    EditText editPassword;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.userEmailAddress);
        editPassword = findViewById(R.id.userPassword);
        email = getIntent().getStringExtra("email");
        userID = getIntent().getIntExtra("id", -1);
        password = getIntent().getStringExtra("password");
        editEmail.setText(email);
        editPassword.setText(password);
        repository = new Repository(getApplication());

        Button newUser = findViewById(R.id.newUserButton);
        newUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(UserLogin.this, NewUser.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(repository.getmAllUsers().isEmpty()){
                    Toast.makeText(UserLogin.this, "Please create a user to login.", Toast.LENGTH_LONG).show();
                }
                else if(editEmail.getText().toString() != null && !editEmail.getText().toString().isEmpty() && editPassword.getText().toString() != null && !editPassword.getText().toString().isEmpty()){
                    for(User i : repository.getmAllUsers()){
                        String e = editEmail.getText().toString();
                        String p = editPassword.getText().toString();

                        if(e.equals(i.getEmail()) && p.equals(i.getPassword())){
                            Intent intent = new Intent(UserLogin.this, HomePage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(UserLogin.this, "Incorrect email or password.", Toast.LENGTH_LONG).show();
                        }
                    }
                }else {
                    Toast.makeText(UserLogin.this, "Please Fill Out All Fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
