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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAdmin extends AppCompatActivity {
    Repository repository;
    String email;
    String password;
    String passcode;
    int adminID;
    EditText editEmail;
    EditText editPassword;
    EditText editPasscode;
    Admin admin;

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
        setContentView(R.layout.activity_new_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editEmail = findViewById(R.id.adminEmailAddress);
        editPassword = findViewById(R.id.adminPassword);
        editPasscode = findViewById(R.id.adminPasscode);
        email = getIntent().getStringExtra("email");
        adminID = getIntent().getIntExtra("id", -1);
        password = getIntent().getStringExtra("password");
        passcode = getIntent().getStringExtra("passcode");
        editEmail.setText(email);
        editPassword.setText(password);
        editPasscode.setText(passcode);
        repository = new Repository(getApplication());

        Button loginAdmin = findViewById(R.id.existingAdminButton);
        loginAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(NewAdmin.this, AdminLogin.class);
                startActivity(intent);
            }
        });

        Button create = findViewById(R.id.loginButton);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (editEmail.getText().toString() != null && !editEmail.getText().toString().isEmpty() && editPassword.getText().toString() != null && !editPassword.getText().toString().isEmpty() && editPasscode.getText().toString() != null && !editPasscode.getText().toString().isEmpty()) {
                    String e = editEmail.getText().toString();
                    String p = editPassword.getText().toString();
                    String c = editPasscode.getText().toString();
                    boolean go = false;
                    if(!repository.getmAllAdmins().isEmpty()){
                    for (Admin i : repository.getmAllAdmins()) {
                        if (e.equals(i.getEmail())) {
                            Toast.makeText(NewAdmin.this, "Account with this email already exists.", Toast.LENGTH_LONG).show();
                        }else{
                            go = true;
                        }
                    }}else{
                        go = true;
                    }
                    if (go) {
                        if (p.length() >= 8) {
                            Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
                            Matcher m = pattern.matcher(c);
                            boolean b = m.find();
                            if (c.length() == 5 && b) {
                                if (emailCheck(e)) {
                                    if (repository.getmAllAdmins().isEmpty()) {
                                        adminID = 1;
                                    } else {
                                        adminID = repository.getmAllAdmins().get(repository.getmAllAdmins().size() - 1).getUserID() + 1;
                                    }
                                    admin = new Admin(adminID, e, p, c);
                                    repository.insert(admin);
                                    Toast.makeText(NewAdmin.this, "Admin Created!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(NewAdmin.this, AdminHomePage.class);
                                    intent.putExtra("admin", adminID);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(NewAdmin.this, "Email must be in valid email address format.", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(NewAdmin.this, "Passcode must be 5 characters and contain a special character.", Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(NewAdmin.this, "Password must contain 8 or more characters.", Toast.LENGTH_LONG).show();

                        }

                    }}
                else{
                            Toast.makeText(NewAdmin.this, "Please Fill Out All Fields", Toast.LENGTH_LONG).show();
                        }
                    }


        });
    }

}
