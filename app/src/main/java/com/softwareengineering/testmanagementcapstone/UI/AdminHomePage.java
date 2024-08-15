package com.softwareengineering.testmanagementcapstone.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softwareengineering.testmanagementcapstone.MainActivity;
import com.softwareengineering.testmanagementcapstone.R;
import com.softwareengineering.testmanagementcapstone.database.Repository;
import com.softwareengineering.testmanagementcapstone.entities.Admin;
import com.softwareengineering.testmanagementcapstone.entities.TestCase;

import java.util.List;

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
        adminID = getIntent().getIntExtra("admin", -1);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomePage.this, TestCaseDetails.class);
                intent.putExtra("admin", adminID);
                startActivity(intent);
            }
        });

        repository = new Repository(getApplication());


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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<TestCase> allTests = repository.getmAllTestCases();
        final TestCaseAdapter testCaseAdapter = new TestCaseAdapter(this);
        recyclerView.setAdapter(testCaseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        testCaseAdapter.setTestCases(allTests, adminID);

    }

    @Override
    protected void onResume(){
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<TestCase> allTests = repository.getmAllTestCases();
        final TestCaseAdapter testCaseAdapter = new TestCaseAdapter(this);
        recyclerView.setAdapter(testCaseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        testCaseAdapter.setTestCases(allTests, adminID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(AdminHomePage.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}
