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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser extends AppCompatActivity {
    Repository repository;
    String email;
    String password;
    int userID;
    EditText editEmail;
    EditText editPassword;
    User user;

    public boolean emailCheck(String e){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(e);
        if(mat.matches()){
            return true;
        }else{
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_user);
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

        Button loginUser = findViewById(R.id.existingUserButton);
        loginUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(NewUser.this, UserLogin.class);
                startActivity(intent);
            }
        });

        Button create = findViewById(R.id.loginButton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editEmail.getText().toString() != null && !editEmail.getText().toString().isEmpty() && editPassword.getText().toString() != null && !editPassword.getText().toString().isEmpty()){
                    String e = editEmail.getText().toString();
                    String p = editPassword.getText().toString();
                    boolean go = false;
                    if (!repository.getmAllUsers().isEmpty()) {
                        for (Admin i : repository.getmAllAdmins()) {
                            if (e.equals(i.getEmail())) {
                                Toast.makeText(NewUser.this, "Account with this email already exists.", Toast.LENGTH_LONG).show();
                            } else {
                                go = true;
                            }
                        }
                    } else {
                        go = true;
                    }
                    if (go) {
                        if (p.length() >= 8) {
                            if (emailCheck(e)) {
                                if (repository.getmAllUsers().isEmpty()) {
                                    userID = 1;
                                } else {
                                    userID = repository.getmAllAdmins().get(repository.getmAllAdmins().size() - 1).getUserID() + 1;
                                }
                                user = new User(userID, e, p);
                                repository.insert(user);
                                Toast.makeText(NewUser.this, "User Created!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(NewUser.this, HomePage.class);
                                intent.putExtra("user", userID);
                                startActivity(intent);
                            } else {
                                Toast.makeText(NewUser.this, "Email must be in valid email address format.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(NewUser.this, "Password must contain 8 or more characters.", Toast.LENGTH_LONG).show();

                        }
                    }}
                else{
                    Toast.makeText(NewUser.this, "Please Fill Out All Fields", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
}
